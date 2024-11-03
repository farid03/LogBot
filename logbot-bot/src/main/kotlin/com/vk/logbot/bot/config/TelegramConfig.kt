package com.vk.logbot.bot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook

@Configuration
class TelegramConfig {

    @Value("\${bot.api-url}")
    lateinit var apiUrl: String

    @Value("\${bot.webhook-path}")
    lateinit var webhookPath: String

    @Value("\${bot.username}")
    lateinit var botUsername: String

    @Value("\${bot.token}")
    lateinit var botToken: String

    @Bean
    fun setWebHook(): SetWebhook {
        return SetWebhook(webhookPath)
    }
}