package com.vk.logbot.bot.util

import com.vk.logbot.bot.model.enm.CallbackType
import com.vk.logbot.bot.temp.Config
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

/**
 * Util-класс для работы с конфигами.
 */
@Component
class ConfigUtils(private val keyboardCreator: KeyboardCreator) {

    /**
     * Создаёт сообщение с инлайн-кнопками, каждая из которых соответствует определённому конфигу.
     */
    fun createConfigListMessage(configs: List<Config>, chatId: Long, callbackType: CallbackType): SendMessage {
        val configsInlineKeyboard = keyboardCreator.createInlineKeyboardMarkup(
            configs.associateBy({ it.name }, { it.id.toString() }),
            callbackType
        )
        val message = SendMessage(chatId.toString(), "Ваши конфигурации:")
        message.replyMarkup = configsInlineKeyboard
        return message
    }
}