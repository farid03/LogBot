package com.vk.logbot.webjmix.view.menu


import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.router.Route
import com.vk.logbot.webjmix.util.TelegramIdProvider
import com.vk.logbot.webjmix.view.configuration.ConfigurationCreateView
import com.vk.logbot.webjmix.view.configuration.ConfigurationListView
import com.vk.logbot.webjmix.view.main.MainView
import io.jmix.flowui.ViewNavigators
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.view.*
import org.springframework.beans.factory.annotation.Autowired

@Route(value = "menu-view", layout = MainView::class)
@ViewController(id = "MenuView")
@ViewDescriptor(path = "menu-view.xml")
class MenuView : StandardView() {

    @Autowired
    private lateinit var telegramIdProvider: TelegramIdProvider

    @Autowired
    private lateinit var viewNavigators: ViewNavigators

    @ViewComponent
    private lateinit var welcomeTextSpan: Span

    @Subscribe
    private fun onInit(event: InitEvent) {
        welcomeTextSpan.text = "Добро пожаловать! Ваш ID: ${telegramIdProvider.getTelegramId()}"
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