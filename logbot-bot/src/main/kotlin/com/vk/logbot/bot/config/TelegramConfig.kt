package com.vk.logbot.bot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.facilities.filedownloader.TelegramFileDownloader
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook

/**
 * Telegram-конфигурация.
 */
@Configuration
class TelegramConfig {

    /**
     * Адрес Telegram API.
     */
    @Value("\${bot.api-url}")
    lateinit var apiUrl: String

    /**
     * URL, по которому бот получает обновления чатов от Telegram.
     */
    @Value("\${bot.webhook-path}")
    lateinit var webhookPath: String

    /**
     * Юзернейм бота.
     */
    @Value("\${bot.username}")
    lateinit var botUsername: String

    /**
     * Токен бота.
     */
    @Value("\${bot.token}")
    lateinit var botToken: String

    /**
     * Возвращает установщик вебхука для бота.
     */
    @Bean
    fun setWebHook(): SetWebhook {
        return SetWebhook(webhookPath)
    }

    /**
     * Возвращает загрузчик файлов из Telegram.
     */
    @Bean
    fun telegramFileDownloader(): TelegramFileDownloader {
        return TelegramFileDownloader { botToken }
    }
}