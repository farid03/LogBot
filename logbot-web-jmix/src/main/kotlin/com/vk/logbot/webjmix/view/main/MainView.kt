package com.vk.logbot.webjmix.view.main

import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import com.vk.logbot.serverrestclient.AuthClient
import com.vk.logbot.serverrestclient.ServerClient
import com.vk.logbot.webjmix.service.SessionDataProvider
import com.vk.logbot.webjmix.util.ConfigDataGridDialogsCreator
import com.vk.logbot.webjmix.util.ConfigDataGridKeyValueEntity
import com.vk.logbot.webjmix.util.ConfigsDataContainerCreator
import com.vk.logbot.webjmix.util.ConfigsDataGridCreator
import io.github.oshai.kotlinlogging.KotlinLogging
import io.jmix.core.entity.KeyValueEntity
import io.jmix.flowui.Notifications
import io.jmix.flowui.app.main.StandardMainView
import io.jmix.flowui.component.grid.DataGrid
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.model.KeyValueCollectionContainer
import io.jmix.flowui.view.Subscribe
import io.jmix.flowui.view.ViewComponent
import io.jmix.flowui.view.ViewController
import io.jmix.flowui.view.ViewDescriptor
import org.springframework.core.env.Environment

@Route("")
@ViewController(id = "MainView")
@ViewDescriptor(path = "main-view.xml")
open class MainView(
    private val environment: Environment,
    private val sessionDataProvider: SessionDataProvider,
    private val authClient: AuthClient,
    private val serverClient: ServerClient,
    private val notifications: Notifications,
    private val configsDataContainerCreator: ConfigsDataContainerCreator,
    private val configsDataGridCreator: ConfigsDataGridCreator,
    private val configsDataGridDialogsCreator: ConfigDataGridDialogsCreator
) : StandardMainView() {

    private val logger = KotlinLogging.logger {}

    @ViewComponent
    private lateinit var layout: VerticalLayout

    @ViewComponent
    private lateinit var editButton: JmixButton

    @ViewComponent
    private lateinit var removeButton: JmixButton

    private lateinit var configsDataGrid: DataGrid<KeyValueEntity>

    private lateinit var configsDataContainer: KeyValueCollectionContainer

    @Subscribe
    fun onInit(event: InitEvent) {
        if (environment.matchesProfiles("dev")) {
            authTelegramUserDev()
            return
        }
        authTelegramUser()
    }

    private fun authTelegramUser() {
        element.executeJs("return window.Telegram.WebApp.initDataUnsafe.user.id")
            .toCompletableFuture()
            .thenAccept {
                val telegramId = it.asNumber().toLong()
                if (checkUserAuth(telegramId)) {
                    sessionDataProvider.setCurrentTelegramId(telegramId)
                    initInterface()
                }
            }.exceptionally {
                notifications.show("Ошибка! Возможно, Вы зашли не через Telegram")
                null
            }
    }

    private fun authTelegramUserDev() {
        val telegramId = sessionDataProvider.getCurrentTelegramId()
        if (telegramId == null) {
            authTelegramUser()
            return
        }
        if (checkUserAuth(telegramId)) {
            initInterface()
        }
    }

    private fun checkUserAuth(telegramId: Long): Boolean {
        try {
            val isAuthenticated = authClient.isAuthenticated(telegramId)
            if (!isAuthenticated) {
                notifications.show("Вы не авторизованы через Telegram-бот!")
            }
            return isAuthenticated
        } catch (e: Exception) {
            notifications.show("Ошибка проверки авторизации")
            logger.error { e.message }
            return false
        }
    }

    private fun initInterface() {
        initConfigsDataGrid()
        layout.isVisible = true
    }

    private fun initConfigsDataGrid() {
        configsDataContainer = configsDataContainerCreator.createConfigsDataContainer()
        configsDataGrid = configsDataGridCreator.createConfigsDataGrid(configsDataContainer)

        configsDataGrid.addSelectionListener {
            val isSelected = it.firstSelectedItem.isPresent
            editButton.isEnabled = isSelected
            removeButton.isEnabled = isSelected
        }

        layout.add(configsDataGrid)
        refreshConfigsDataGrid()
    }

    private fun refreshConfigsDataGrid() {
        val telegramId = sessionDataProvider.getCurrentTelegramId()!!
        try {
            val configs = serverClient.getConfigsByUserId(telegramId)
            val configsKeyValueEntities = configs.map {
                ConfigDataGridKeyValueEntity.fromConfigDto(it)
            }
            configsDataContainer.setItems(configsKeyValueEntities)
        } catch (e: Exception) {
            notifications.show("Ошибка обновления списка конфигураций!")
            logger.error { e.message }
        }
    }

    @Subscribe(id = "createButton", subject = "clickListener")
    private fun onCreateButtonClick(event: ClickEvent<JmixButton>) {
        configsDataGridDialogsCreator.openCreateDialog(this) { closeEvent ->
            val telegramId = sessionDataProvider.getCurrentTelegramId()!!

            val name = closeEvent.getValue<String>("name")!!
            val regExp = closeEvent.getValue<String>("regExp")!!
            val message = closeEvent.getValue<String>("message")!!

            try {
                serverClient.createConfig(
                    telegramId,
                    name,
                    regExp,
                    message
                )
                refreshConfigsDataGrid()
            } catch (e: Exception) {
                notifications.show("Ошибка создания конфигурации!")
                logger.error { e.message }
            }
        }
    }

    @Subscribe(id = "editButton", subject = "clickListener")
    private fun onEditButtonClick(event: ClickEvent<JmixButton>) {
        val selectedConfig = configsDataGrid.singleSelectedItem ?: return
        val configId = selectedConfig.getValue<Long>(ConfigDataGridKeyValueEntity.ID)
        val config = serverClient.getConfigById(configId)

        configsDataGridDialogsCreator.openEditDialog(this, config) { closeEvent ->
            val name = closeEvent.getValue<String>("name")!!
            val regExp = closeEvent.getValue<String>("regExp")!!
            val message = closeEvent.getValue<String>("message")!!
            val isActive = closeEvent.getValue<Boolean>("isActive")!!

            try {
                serverClient.editConfig(
                    configId,
                    name,
                    regExp,
                    message,
                    isActive
                )
                refreshConfigsDataGrid()
            } catch (e: Exception) {
                notifications.show("Ошибка изменения конфигурации!")
                logger.error { e.message }
            }
        }
    }

    @Subscribe(id = "removeButton", subject = "clickListener")
    private fun onRemoveButtonClick(event: ClickEvent<JmixButton>) {
        val selectedConfig = configsDataGrid.singleSelectedItem ?: return
        val configId = selectedConfig.getValue<Long>(ConfigDataGridKeyValueEntity.ID)

        try {
            serverClient.deleteConfig(configId)
            refreshConfigsDataGrid()
        } catch (e: Exception) {
            notifications.show("Ошибка удаления конфигурации!")
            logger.error { e.message }
        }
    }

    @Subscribe(id = "refreshButton", subject = "clickListener")
    private fun onRefreshButtonClick(event: ClickEvent<JmixButton>) {
        refreshConfigsDataGrid()
    }
}