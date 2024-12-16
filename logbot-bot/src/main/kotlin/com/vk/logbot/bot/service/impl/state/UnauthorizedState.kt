package com.vk.logbot.bot.service.impl.state

import com.vk.logbot.bot.annotation.BotState
import com.vk.logbot.bot.model.StateNames
import com.vk.logbot.bot.service.BotApiMethodExecutor
import com.vk.logbot.bot.service.ChatInfoService
import com.vk.logbot.bot.service.State
import com.vk.logbot.bot.service.StateContext
import com.vk.logbot.bot.util.KeyboardCreator
import com.vk.logbot.serverrestclient.AuthClient
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove

/**
 * Состояние до авторизации.
 */
@BotState(StateNames.UNAUTHORIZED)
class UnauthorizedState(
    stateContext: StateContext,
    botApiMethodExecutor: BotApiMethodExecutor,
    keyboardCreator: KeyboardCreator,
    private val chatInfoService: ChatInfoService,
    private val authClient: AuthClient
) : State(stateContext, botApiMethodExecutor, keyboardCreator, emptyMap()) {

    override fun initState(chatId: Long) {
        val isAuthorized = chatInfoService.getChatInfoByChatId(chatId)?.isAuthorized ?: false
        if (isAuthorized) {
            stateContext.switchState(chatId, StateNames.MAIN_MENU)
            return
        }

        val initMessage = SendMessage(chatId.toString(), "Введите код для авторизации")
        initMessage.replyMarkup = ReplyKeyboardRemove(true)
        botApiMethodExecutor.executeBotApiMethod(initMessage)
    }

    override fun handleNotCommandMessage(chatId: Long, message: Message) {
        if (!message.hasText()) {
            return super.handleNotCommandMessage(chatId, message)
        }

        if (authClient.authTelegramUser(message.from.id, message.text)) {
            chatInfoService.updateChatInfoIsAuthorized(chatId, true)
            stateContext.switchState(chatId, StateNames.MAIN_MENU)
            return
        }

        botApiMethodExecutor.executeBotApiMethod(
            SendMessage(
                chatId.toString(),
                "Код некорректен! Попробуйте снова"
            )
        )
    }
}