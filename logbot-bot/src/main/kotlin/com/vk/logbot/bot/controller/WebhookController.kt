package com.vk.logbot.bot.controller

import com.vk.logbot.bot.LogBot
import com.vk.logbot.bot.exception.BotException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@RestController
class WebhookController(val logBot: LogBot) {

	@PostMapping("/callback/update")
	fun onWebhookUpdateReceived(@RequestBody update: Update): BotApiMethod<*> {
		return logBot.onWebhookUpdateReceived(update)
	}

	@ExceptionHandler(BotException::class)
	fun handleBotException(e: BotException): BotApiMethod<*> {
		return SendMessage(e.chatId, e.publicMessage)
	}
}