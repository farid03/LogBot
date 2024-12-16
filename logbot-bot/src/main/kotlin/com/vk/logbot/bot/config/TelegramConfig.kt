package com.vk.logbot.bot.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.facilities.filedownloader.TelegramFileDownloader
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook
import org.telegram.telegrambots.meta.api.objects.InputFile
import java.io.File

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
     * Путь к самоподписанному сертификату.
     */
    lateinit var certPath: String

    /**
     * Возвращает установщик вебхука для бота.
     */
    @Bean
    fun setWebHook(): SetWebhook {
        val setWebhook = SetWebhook(hostUrl)
        if (certPath.isNotBlank()) {
            setWebhook.certificate = InputFile(File(certPath))
        }
        return setWebhook
    }

    /**
     * Возвращает загрузчик файлов из Telegram.
     */
    @Bean
    fun telegramFileDownloader(): TelegramFileDownloader {
        return TelegramFileDownloader { token }
    }
}