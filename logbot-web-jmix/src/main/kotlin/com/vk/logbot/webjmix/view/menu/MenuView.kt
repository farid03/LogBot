package com.vk.logbot.webjmix.view.menu

import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.router.Route
import com.vk.logbot.webjmix.service.SessionDataProvider
import com.vk.logbot.webjmix.view.configuration.ConfigurationCreateView
import com.vk.logbot.webjmix.view.configuration.ConfigurationListView
import com.vk.logbot.webjmix.view.main.MainView
import io.jmix.flowui.ViewNavigators
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.view.*

@Route(value = "menu-view", layout = MainView::class)
@ViewController(id = "MenuView")
@ViewDescriptor(path = "menu-view.xml")
class MenuView(
    private val sessionDataProvider: SessionDataProvider,
    private val viewNavigators: ViewNavigators
) : StandardView() {

    @ViewComponent
    private lateinit var welcomeTextSpan: Span

    @Subscribe
    private fun onInit(event: InitEvent) {
        welcomeTextSpan.text = "Добро пожаловать! Ваш ID: ${sessionDataProvider.getCurrentTelegramId()}"
    }

    @Subscribe(id = "showConfigurationsButton", subject = "clickListener")
    private fun onShowConfigurationsButtonClick(event: ClickEvent<JmixButton>) {
        viewNavigators.view(this, ConfigurationListView::class.java).navigate()
    }

    @Subscribe(id = "createConfigurationButton", subject = "clickListener")
    private fun onCreateConfigurationButtonClick(event: ClickEvent<JmixButton>) {
        viewNavigators.view(this, ConfigurationCreateView::class.java).navigate()
    }
}