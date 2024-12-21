package com.vk.logbot.bot.service.impl

import com.vk.logbot.bot.exception.BotException
import com.vk.logbot.bot.model.StateNames
import com.vk.logbot.bot.service.BotApiMethodExecutor
import com.vk.logbot.bot.service.ChatInfoService
import com.vk.logbot.bot.service.State
import com.vk.logbot.bot.service.StateContext
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class StateContextImpl(
    private val chatInfoService: ChatInfoService,
    private val applicationContext: ApplicationContext,
    private val botApiMethodExecutor: BotApiMethodExecutor
) : StateContext {

    private val logger = KotlinLogging.logger {}

    /**
     * Начальное состояние.
     */
    private val initialStateName = StateNames.UNAUTHORIZED

    override fun handleUpdate(update: Update) {
        val chatId =
            if (update.hasCallbackQuery()) {
                getChatId(update.callbackQuery)
            } else {
                getChatId(update.message)
            }
        val userId =
            if (update.hasCallbackQuery()) {
                update.callbackQuery.from.id
            } else {
                update.message.from.id
            }

        try {
            val chatInfo = chatInfoService.getChatInfoByChatId(chatId) ?: let {
                chatInfoService.createNewChatInfo(chatId, userId)
                switchToStartState(chatId)
                return
            }

            val stateName = chatInfo.lastStateName ?: let {
                switchToStartState(chatId)
                return
            }

            val isAuthorized = chatInfoService.getChatInfoByChatId(chatId)?.isAuthorized ?: false
            if (!isAuthorized && stateName != initialStateName) {
                switchToStartState(chatId)
                return
            }

            val state = getStateByName(stateName)
            if (update.hasCallbackQuery()) {
                state.handleCallbackQuery(chatId, update.callbackQuery)
            } else {
                state.handleMessage(chatId, update.message)
            }
        } catch (e: Throwable) {
            handleException(chatId, e);
        }
    }

    override fun switchState(chatId: Long, stateName: String) {
        val state = getStateByName(stateName)
        chatInfoService.updateChatInfoLastState(chatId, stateName)
        state.initState(chatId)
    }

    override fun switchToStartState(chatId: Long) {
        switchState(chatId, initialStateName)
    }

    /**
     * Возвращает id чата для сообщения.
     */
    private fun getChatId(message: Message): Long {
        return message.chatId
    }

    /**
     * Возвращает id чата для коллбэка.
     */
    private fun getChatId(callbackQuery: CallbackQuery): Long {
        return callbackQuery.message.chatId.toLong()
    }

    /**
     * Возвращает бин состояния по его названию.
     */
    private fun getStateByName(stateName: String): State {
        return applicationContext.getBean(stateName) as State
    }

    /**
     * Перехватывает сообщение об ошибке и отправляет в чат "Ошибка", если исключение не наследуется от [BotException],
     * либо публичное сообщение [BotException].
     */
    private fun handleException(chatId: Long, exception: Throwable) {
        logger.error { exception.message }
        val answerText =
            if (exception is BotException) {
                exception.publicMessage
            } else {
                "Ошибка!"
            }
        botApiMethodExecutor.executeBotApiMethod(SendMessage(chatId.toString(), answerText))
    }
}