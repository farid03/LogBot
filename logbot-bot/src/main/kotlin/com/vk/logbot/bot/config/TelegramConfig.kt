package com.vk.logbot.bot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.facilities.filedownloader.TelegramFileDownloader
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook

@Configuration
class TelegramConfig {

	@Value("\${telegram.api-url}")
	lateinit var apiUrl: String

	@Value("\${telegram.webhook-path}")
	lateinit var webhookPath: String

	@Value("\${telegram.bot-username}")
	lateinit var botUsername: String

	@Value("\${telegram.bot-token}")
	lateinit var botToken: String

	@Bean
	fun setWebHook(): SetWebhook {
		return SetWebhook(webhookPath)
	}

	@Bean
	fun telegramFileDownloader(): TelegramFileDownloader {
		return TelegramFileDownloader { botToken }
	}
}