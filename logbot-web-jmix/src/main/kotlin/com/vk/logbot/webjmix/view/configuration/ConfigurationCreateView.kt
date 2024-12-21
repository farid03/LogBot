package com.vk.logbot.webjmix.view.configuration


import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.router.Route
import com.vk.logbot.serverrestclient.ServerClient
import com.vk.logbot.webjmix.service.SessionDataProvider
import com.vk.logbot.webjmix.view.main.MainView
import com.vk.logbot.webjmix.view.menu.MenuView
import io.jmix.flowui.Notifications
import io.jmix.flowui.ViewNavigators
import io.jmix.flowui.component.textfield.TypedTextField
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.view.*
import org.springframework.beans.factory.annotation.Autowired
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

@Route(value = "configuration-create-view", layout = MainView::class)
@ViewController(id = "ConfigurationCreateView")
@ViewDescriptor(path = "configuration-create-view.xml")
class ConfigurationCreateView : StandardView() {

    @Autowired
    private lateinit var serverClient: ServerClient

    @Autowired
    private lateinit var sessionDataProvider: SessionDataProvider

    @Autowired
    private lateinit var notifications: Notifications

    @Autowired
    private lateinit var viewNavigators: ViewNavigators

    @ViewComponent
    private lateinit var nameTextField: TypedTextField<String>

    @ViewComponent
    private lateinit var regExpTextField: TypedTextField<String>

    @ViewComponent
    private lateinit var messageTextField: TypedTextField<String>

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
            val newConfig = serverClient.createConfig(
                sessionDataProvider.getCurrentTelegramId()!!,
                nameTextField.value,
                regExpTextField.value,
                messageTextField.value,
            )
            notifications.show(
                "Конфигурация успешно создана!\n" +
                        "ID: \"${newConfig.id}\"\n" +
                        "Название: \"${newConfig.name}\"\n" +
                        "Регулярное выражение: \"${newConfig.regExp}\"\n" +
                        "Сообщение: \"${newConfig.message}\""
            )
        } catch (ex: Exception) {
            notifications.show("Ошибка сохранения конфигурации!")
        }
    }

    @Subscribe(id = "backButton", subject = "clickListener")
    private fun onBackButtonClick(event: ClickEvent<JmixButton>) {
        viewNavigators.view(this, MenuView::class.java).navigate()
    }
}