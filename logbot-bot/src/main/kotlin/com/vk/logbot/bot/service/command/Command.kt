package com.vk.logbot.bot.service.command

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message

interface Command {

	fun execute(message: Message): BotApiMethod<*>

	fun getChatId(message: Message): String {
		return message.chat.id.toString()
	}
}