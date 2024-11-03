package com.vk.logbot.bot.service.state

import com.vk.logbot.bot.enm.Command
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

abstract class State(
    var previewState: State?,
    var nextStates: MutableMap<Command, State>,
    val keyboard: ReplyKeyboardMarkup
) {

    abstract fun handleMessage(message: Message): BotApiMethod<*>

    open fun terminalHandleMessage(message: Message): BotApiMethod<*> {
        return handleMessage(message)
    }

    open fun handleCallbackQuery(callbackQuery: CallbackQuery): BotApiMethod<*> {
        return createSendMessage(callbackQuery, "Неизвестная команда")
    }

    fun nextStatesIsEmpty(): Boolean {
        return nextStates.isEmpty()
    }

    fun containsCommand(command: Command): Boolean {
        return nextStates.containsKey(command)
    }

    fun getNextState(command: Command): State? {
        return nextStates[command]
    }

    open fun clearInputData(chatId: Long) {}

    private fun getChatId(message: Message): String {
        return message.chatId.toString()
    }

    private fun getChatId(callbackQuery: CallbackQuery): String {
        return callbackQuery.message.chatId.toString()
    }

    protected fun createSendMessage(message: Message, text: String): SendMessage {
        return createSendMessage(getChatId(message), text, keyboard)
    }

    protected fun createSendMessage(message: Message, text: String, replyKeyboard: ReplyKeyboard): SendMessage {
        return createSendMessage(getChatId(message), text, replyKeyboard)
    }

    protected fun createSendMessage(callbackQuery: CallbackQuery, text: String): SendMessage {
        return createSendMessage(getChatId(callbackQuery), text, keyboard)
    }

    protected fun createSendMessage(
        callbackQuery: CallbackQuery,
        text: String,
        replyKeyboard: ReplyKeyboard
    ): SendMessage {
        return createSendMessage(getChatId(callbackQuery), text, replyKeyboard)
    }

    private fun createSendMessage(chatId: String, text: String, replyKeyboard: ReplyKeyboard): SendMessage {
        val sendMessage = SendMessage(chatId, text)
        sendMessage.replyMarkup = replyKeyboard
        return sendMessage
    }
}