package com.vk.logbot.bot.util

import com.vk.logbot.bot.model.enm.CallbackType
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

/**
 * Конструктор клавиатур.
 */
@Component
class KeyboardCreator(private val callbackUtils: CallbackUtils) {

    /**
     * Создаёт постоянную (нижнюю) клавиатуру чата.
     */
    fun createReplyKeyboardMarkup(textRows: List<String>): ReplyKeyboardMarkup {
        val keyboardRows = ArrayList<KeyboardRow>()
        for (row in textRows) {
            val keyboardRow = KeyboardRow()
            keyboardRow.add(row)
            keyboardRows.add(keyboardRow)
        }

        val replyKeyboardMarkup = ReplyKeyboardMarkup()
        replyKeyboardMarkup.keyboard = keyboardRows
        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        return replyKeyboardMarkup
    }

    /**
     * Создаёт клавиатуру с инлайн-кнопками (нажатие на которые создаёт коллбэк-запрос).
     */
    fun createInlineKeyboardMarkup(textDataMap: Map<String, String>, callbackType: CallbackType): InlineKeyboardMarkup {
        return InlineKeyboardMarkup(
            textDataMap.entries.map { mutableListOf(createInlineButton(it.key, it.value, callbackType)) }
        )
    }

    /**
     * Создаёт инлайн-кнопку.
     */
    private fun createInlineButton(text: String, data: String, callbackType: CallbackType): InlineKeyboardButton {
        val button = InlineKeyboardButton(text)
        button.callbackData = callbackUtils.createCallbackData(callbackType, data)
        return button
    }
}