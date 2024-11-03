package com.vk.logbot.bot.service.state

import com.vk.logbot.bot.enm.Command
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

class AuthorizedState(
    previewState: State?,
    nextStates: MutableMap<Command, State>,
    keyboard: ReplyKeyboardMarkup
) : State(previewState, nextStates, keyboard) {

    override fun handleMessage(message: Message): BotApiMethod<*> {
        return createSendMessage(message, "Добро пожаловать! Выберите команду в меню")
    }
}