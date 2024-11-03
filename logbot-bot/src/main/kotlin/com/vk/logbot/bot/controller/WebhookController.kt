package com.vk.logbot.bot.controller

import com.vk.logbot.bot.exception.BotException
import com.vk.logbot.bot.service.CallbackQueryHandler
import com.vk.logbot.bot.service.MessageHandler
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@RestController
class WebhookController(
    private val callbackQueryHandler: CallbackQueryHandler,
    private val messageHandler: MessageHandler
) {

    @PostMapping("/callback/update")
    fun onWebhookUpdateReceived(@RequestBody update: Update): BotApiMethod<*> {
        if (update.hasCallbackQuery()) {
            return callbackQueryHandler.handle(update.callbackQuery)
        }
        return messageHandler.handle(update.message)
    }

    @ExceptionHandler(BotException::class)
    fun handleBotException(e: BotException): BotApiMethod<*> {
        return SendMessage(e.chatId.toString(), e.publicMessage)
    }
}