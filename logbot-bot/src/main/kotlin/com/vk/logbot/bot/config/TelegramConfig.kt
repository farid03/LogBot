package com.vk.logbot.bot.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.facilities.filedownloader.TelegramFileDownloader
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook

/**
 * Telegram-конфигурация.
 */
@Configuration
@ConfigurationProperties(prefix = "bot")
class TelegramConfig {

    /**
     * Адрес Telegram API.
     */
    lateinit var apiUrl: String

    /**
     * URL, по которому бот получает обновления чатов от Telegram.
     */
    lateinit var hostUrl: String

    /**
     * Токен бота.
     */
    lateinit var token: String

    /**
     * Возвращает установщик вебхука для бота.
     */
    @Bean
    fun setWebHook(): SetWebhook {
        return SetWebhook(hostUrl)
    }

    /**
     * Возвращает загрузчик файлов из Telegram.
     */
    @Bean
    fun telegramFileDownloader(): TelegramFileDownloader {
        return TelegramFileDownloader { token }
    }
}