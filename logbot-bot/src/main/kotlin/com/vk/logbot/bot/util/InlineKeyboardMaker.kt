package com.vk.logbot.bot.util

import com.vk.logbot.bot.enm.CallbackDataType
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

@Component
class InlineKeyboardMaker(private val callbackUtils: CallbackUtils) {

    fun createKeyboard(buttons: List<String>, callbackDataType: CallbackDataType, userId: Long): InlineKeyboardMarkup {
        return InlineKeyboardMarkup(buttons.map { b -> mutableListOf(createButton(b, callbackDataType, userId)) })
    }

    private fun createButton(text: String, callbackDataType: CallbackDataType, userId: Long): InlineKeyboardButton {
        val button = InlineKeyboardButton(text)
        button.callbackData = callbackUtils.createCallbackData(callbackDataType, userId, text)
        return button
    }
}