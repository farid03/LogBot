package com.vk.logbot.bot

import com.vk.logbot.bot.config.TelegramConfig
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramWebhookBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class LogBot(
    val telegramConfig: TelegramConfig
) : TelegramWebhookBot(telegramConfig.botToken) {

    init {
        setWebhook(telegramConfig.setWebHook())
    }

    override fun getBotPath(): String {
        return "/update"
    }

    override fun getBotUsername(): String {
        return telegramConfig.botUsername
    }

    override fun onWebhookUpdateReceived(update: Update?): BotApiMethod<*> {
        TODO("Метод не требуется")
    }
}