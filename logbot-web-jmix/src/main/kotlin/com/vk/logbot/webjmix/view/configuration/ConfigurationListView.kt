package com.vk.logbot.webjmix.view.configuration


import com.vaadin.flow.component.AbstractField
import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.data.renderer.ComponentRenderer
import com.vaadin.flow.router.Route
import com.vaadin.flow.theme.lumo.LumoUtility
import com.vk.logbot.commons.dto.ConfigDto
import com.vk.logbot.serverrestclient.ServerClient
import com.vk.logbot.webjmix.service.SessionDataProvider
import com.vk.logbot.webjmix.view.main.MainView
import com.vk.logbot.webjmix.view.menu.MenuView
import io.jmix.flowui.Notifications
import io.jmix.flowui.UiComponents
import io.jmix.flowui.ViewNavigators
import io.jmix.flowui.component.listbox.JmixListBox
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.view.*
import org.springframework.beans.factory.annotation.Autowired


@Route(value = "configuration-list-view", layout = MainView::class)
@ViewController(id = "ConfigurationListView")
@ViewDescriptor(path = "configuration-list-view.xml")
class ConfigurationListView : StandardView() {

    @Autowired
    private lateinit var serverClient: ServerClient

    @Autowired
    private lateinit var sessionDataProvider: SessionDataProvider

    @Autowired
    private lateinit var viewNavigators: ViewNavigators

    @Autowired
    private lateinit var uiComponents: UiComponents

    @Autowired
    private lateinit var notifications: Notifications

    @ViewComponent
    private lateinit var configsListBox: JmixListBox<ConfigDto>

    @Supply(to = "configsListBox", subject = "renderer")
    private fun configsListBoxRenderer(): ComponentRenderer<HorizontalLayout, ConfigDto> {
        return ComponentRenderer<HorizontalLayout, ConfigDto>() { config ->
            val row = uiComponents.create(HorizontalLayout::class.java)

            val nameSpan = Span(config.name)
            val messageSpan = Span(config.message)
            messageSpan.addClassNames(LumoUtility.TextColor.SECONDARY, LumoUtility.FontSize.SMALL)

            val column = uiComponents.create(VerticalLayout::class.java)
            column.add(nameSpan, messageSpan)

            row.add(column)
            row
        }
    }

    @Subscribe
    private fun onInit(event: InitEvent) {
        try {
            val configurations = serverClient.getConfigsByUserId(sessionDataProvider.getCurrentTelegramId()!!)
            configsListBox.setItems(configurations)
        } catch (ex: Exception) {
            notifications.show("Ошибка загрузки списка конфигураций!")
            viewNavigators.view(this, MenuView::class.java).navigate()
            return
        }
    }

    @Subscribe("configsListBox")
    private fun onConfigsListBoxComponentValueChange(event: AbstractField.ComponentValueChangeEvent<JmixListBox<ConfigDto>, ConfigDto>) {
        val selectedConfig = event.value
        sessionDataProvider.setCurrentConfigId(selectedConfig.id)
        viewNavigators.view(this, ConfigurationEditView::class.java).navigate()
    }

    @Subscribe(id = "backButton", subject = "clickListener")
    private fun onBackButtonClick(event: ClickEvent<JmixButton>) {
        viewNavigators.view(this, MenuView::class.java).navigate()
    }
}