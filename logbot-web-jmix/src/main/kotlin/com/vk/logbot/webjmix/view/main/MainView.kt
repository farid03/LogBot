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
import org.springframework.beans.factory.annotation.Autowired

@Route("")
@ViewController(id = "MainView")
@ViewDescriptor(path = "main-view.xml")
open class MainView : StandardMainView() {

    @Autowired
    private lateinit var sessionDataProvider: SessionDataProvider

    @Autowired
    private lateinit var notifications: Notifications

    @Autowired
    private lateinit var viewNavigators: ViewNavigators

    @Subscribe
    fun onInit(event: InitEvent) {
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
}
