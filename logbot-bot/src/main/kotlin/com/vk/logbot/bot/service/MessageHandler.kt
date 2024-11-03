package com.vk.logbot.bot.service

import com.vk.logbot.bot.enm.Command
import com.vk.logbot.bot.exception.BotIllegalCommandException
import com.vk.logbot.bot.exception.BotIllegalMessageContentException
import com.vk.logbot.bot.service.cache.StateCache
import com.vk.logbot.bot.service.state.AuthorizedState
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class MessageHandler(
    private val stateCache: StateCache,
    private val authorizedState: AuthorizedState
) {
    private val firstState = authorizedState

    fun handle(message: Message): BotApiMethod<*> {
        val chatId = message.chatId

        if (message.text == null && !message.hasDocument()) {
            throw BotIllegalMessageContentException(chatId)
        }

        if (Command.START.text == message.text) {
            return handleFirstState(message)
        }

        val state = stateCache.get(chatId)
        val command = Command.getByText(message.text ?: "")
        if (state == null) {
            stateCache.put(chatId, firstState)
            if (command != null && firstState.containsCommand(command)) {
                return firstState.handleMessage(message)
            }
            return handleNoState(chatId.toString())
        }

        if (Command.BACK.text == message.text) {
            val newState = state.previewState
            if (newState != null) {
                state.clearInputData(message.chatId)
                stateCache.put(chatId, newState)
                return newState.handleMessage(message)
            }
            return state.handleMessage(message)
        }

        if (state.nextStatesIsEmpty()) {
            return state.terminalHandleMessage(message)
        }

        if (command == null || !state.containsCommand(command)) {
            throw BotIllegalCommandException(command.toString(), chatId)
        }

        val newState = state.getNextState(command)
        stateCache.put(chatId, newState!!)
        return newState.handleMessage(message)
    }

    private fun handleFirstState(message: Message): BotApiMethod<*> {
        stateCache.put(message.chatId, authorizedState)
        return authorizedState.handleMessage(message)
    }

    private fun handleNoState(chatId: String): BotApiMethod<*> {
        val sendMessage = SendMessage(chatId, "Вы долго отсутствовали. Пожалуйста, начните сначала")
        sendMessage.replyMarkup = firstState.keyboard
        return sendMessage
    }
}