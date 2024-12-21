package com.vk.logbot.webjmix.view.configuration

import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.router.Route
import com.vk.logbot.commons.dto.ConfigDto
import com.vk.logbot.serverrestclient.ServerClient
import com.vk.logbot.webjmix.service.SessionDataProvider
import com.vk.logbot.webjmix.view.main.MainView
import io.jmix.flowui.Notifications
import io.jmix.flowui.ViewNavigators
import io.jmix.flowui.component.checkbox.JmixCheckbox
import io.jmix.flowui.component.textfield.TypedTextField
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.view.*
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

@Route(value = "configuration-edit-view", layout = MainView::class)
@ViewController(id = "ConfigurationEditView")
@ViewDescriptor(path = "configuration-edit-view.xml")
class ConfigurationEditView(
    private val serverClient: ServerClient,
    private val sessionDataProvider: SessionDataProvider,
    private val notifications: Notifications,
    private val viewNavigators: ViewNavigators
) : StandardView() {

    @ViewComponent
    private lateinit var nameTextField: TypedTextField<String>

    @ViewComponent
    private lateinit var regExpTextField: TypedTextField<String>

    @ViewComponent
    private lateinit var messageTextField: TypedTextField<String>

    @ViewComponent
    private lateinit var isActiveCheckbox: JmixCheckbox

    @Subscribe
    private fun onInit(event: InitEvent) {
        val currentConfigId = sessionDataProvider.getCurrentConfigId()

        if (currentConfigId == null) {
            notifications.show("Конфиг не выбран!")
            viewNavigators.view(this, ConfigurationListView::class.java)
            return
        }

        val currentConfig: ConfigDto
        try {
            currentConfig = serverClient.getConfigById(currentConfigId)
        } catch (ex: Exception) {
            notifications.show("Ошибка загрузки конфига!")
            viewNavigators.view(this, ConfigurationListView::class.java)
            return
        }

        nameTextField.value = currentConfig.name
        regExpTextField.value = currentConfig.regExp
        messageTextField.value = currentConfig.message
        isActiveCheckbox.value = currentConfig.active
    }

    @Subscribe(id = "createButton", subject = "clickListener")
    private fun onCreateButtonClick(event: ClickEvent<JmixButton>) {
        if (nameTextField.value.isEmpty() || regExpTextField.value.isEmpty() || messageTextField.value.isEmpty()) {
            notifications.show("Заполните все поля!")
            return
        }

        try {
            Pattern.compile(regExpTextField.value)
        } catch (ex: PatternSyntaxException) {
            notifications.show("Регулярное выражение некорректно!")
            return
        }

        try {
            serverClient.editConfig(
                sessionDataProvider.getCurrentConfigId()!!,
                nameTextField.value,
                regExpTextField.value,
                messageTextField.value,
                isActiveCheckbox.value
            )
            notifications.show("Конфигурация успешно изменена!")
        } catch (ex: Exception) {
            notifications.show("Ошибка сохранения конфигурации!")
        }
    }

    @Subscribe(id = "removeButton", subject = "clickListener")
    private fun onRemoveButtonClick(event: ClickEvent<JmixButton>) {
        try {
            serverClient.deleteConfig(sessionDataProvider.getCurrentConfigId()!!)
            notifications.show("Конфигурация успешно удалена!")
            viewNavigators.view(this, ConfigurationListView::class.java).navigate()
        } catch (ex: Exception) {
            notifications.show("Ошибка удаления конфигурации!")
        }
    }

    @Subscribe(id = "backButton", subject = "clickListener")
    private fun onBackButtonClick(event: ClickEvent<JmixButton>) {
        viewNavigators.view(this, ConfigurationListView::class.java).navigate()
    }
}