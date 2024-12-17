package com.vk.logbot.bot

import com.vk.logbot.bot.config.TelegramConfig
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramWebhookBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class LogBot(
    private val telegramConfig: TelegramConfig
) : TelegramWebhookBot(telegramConfig.token) {

    init {
        setWebhook(telegramConfig.setWebHook())
    }

    override fun getBotPath(): String {
        return "/update/${telegramConfig.token}"
    }

    override fun getBotUsername(): String? {
        return null
    }

    override fun onWebhookUpdateReceived(update: Update?): BotApiMethod<*>? {
        return null
    }
}