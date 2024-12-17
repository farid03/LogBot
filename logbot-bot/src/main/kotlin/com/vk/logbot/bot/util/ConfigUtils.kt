package com.vk.logbot.bot.util

import com.vk.logbot.bot.model.enm.CallbackType
import com.vk.logbot.bot.temp.Config
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup

/**
 * Util-класс для работы с конфигами.
 */
@Component
class ConfigUtils(private val keyboardCreator: KeyboardCreator) {

    /**
     * Создаёт сообщение с инлайн-кнопками, каждая из которых соответствует определённому конфигу.
     */
    fun createConfigListMessage(
        configs: List<Config>,
        chatId: Long,
        callbackType: CallbackType,
        markActive: Boolean = false
    ): SendMessage {
        val configsInlineKeyboard = keyboardCreator.createInlineKeyboardMarkup(
            configs.sortedBy { it.id }
                .associateBy(
                    { "${it.name} ${if (markActive) getActiveMark(it) else ""}".trim() },
                    { it.id.toString() }),
            callbackType
        )
        val message = SendMessage(chatId.toString(), "Ваши конфигурации:")
        message.replyMarkup = configsInlineKeyboard
        message.enableMarkdownV2(true)
        return message
    }

    /**
     * Создаёт инлайн-клавиатуру для выбора активных конфигов. Содержит в себе кнопку, которая делает все конфиги
     * неактивными.
     */
    fun createConfigsKeyboardWithActiveMarks(configs: List<Config>): InlineKeyboardMarkup {
        val keyboard = keyboardCreator.createInlineKeyboardMarkup(
            configs.sortedBy { it.id }.associateBy(
                { "${it.name} ${getActiveMark(it)}" },
                { it.id.toString() }
            ), CallbackType.MAIN_MENU_ACTIVE_CONFIGURATION_CHOICE
        )
        keyboard.keyboard.add(
            mutableListOf(
                keyboardCreator.createInlineButton(
                    "Сделать все неактивными",
                    "-1",
                    CallbackType.MAIN_MENU_ACTIVE_CONFIGURATION_CHOICE
                )
            )
        )
        return keyboard
    }

    /**
     * Возвращает галочку, если конфиг активен, иначе - крестик.
     */
    private fun getActiveMark(config: Config): String {
        if (config.active) {
            return "✅"
        }
        return "❌"
    }
}