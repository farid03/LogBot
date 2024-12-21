package com.vk.logbot.webjmix.view.main

import com.vaadin.flow.router.Route
import com.vk.logbot.webjmix.service.SessionDataProvider
import com.vk.logbot.webjmix.view.menu.MenuView
import io.jmix.flowui.Notifications
import io.jmix.flowui.ViewNavigators
import io.jmix.flowui.app.main.StandardMainView
import io.jmix.flowui.view.Subscribe
import io.jmix.flowui.view.ViewController
import io.jmix.flowui.view.ViewDescriptor
import org.springframework.core.env.Environment

@Route("")
@ViewController(id = "MainView")
@ViewDescriptor(path = "main-view.xml")
open class MainView(
    private val environment: Environment,
    private val sessionDataProvider: SessionDataProvider,
    private val notifications: Notifications,
    private val viewNavigators: ViewNavigators
) : StandardMainView() {

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
                //todo: проверка, что пользователь авторизован
                sessionDataProvider.setCurrentTelegramId(telegramId)
                viewNavigators.view(this, MenuView::class.java).navigate()
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
        //todo: проверка, что пользователь авторизован
        viewNavigators.view(this, MenuView::class.java).navigate()
    }
}
