package com.vk.logbot.bot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

@Service
class CallbackQueryHandler {

    fun handle(callbackQuery: CallbackQuery): BotApiMethod<*> {
        TODO()
    }
}