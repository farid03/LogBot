package com.vk.logbot.bot

import com.vk.logbot.bot.config.TelegramConfig
import com.vk.logbot.bot.service.CallbackQueryHandler
import com.vk.logbot.bot.service.MessageHandler
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramWebhookBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class LogBot(
	val telegramConfig: TelegramConfig,
	val callbackQueryHandler: CallbackQueryHandler,
	val messageHandler: MessageHandler
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

	override fun onWebhookUpdateReceived(update: Update): BotApiMethod<*> {
		if (update.hasCallbackQuery()) {
			return callbackQueryHandler.handle(update.callbackQuery)
		}
		return messageHandler.handle(update.message)
	}
}