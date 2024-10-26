package com.vk.logbot.bot.service

import com.vk.logbot.bot.exception.BotCommandNotFoundException
import com.vk.logbot.bot.service.command.Command
import com.vk.logbot.bot.service.command.FileCommand
import com.vk.logbot.bot.service.command.StartCommand
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class MessageHandler(
	startCommand: StartCommand,
	uploadConfigFileCommand: FileCommand.UploadConfigFileCommand
) {

	private val commands: MutableMap<String, Command> = HashMap()
	private val fileCommand = uploadConfigFileCommand

	init {
		commands["/start"] = startCommand
	}

	fun handle(message: Message): BotApiMethod<*> {
		if (message.hasDocument()) {
			return fileCommand.execute(message)
		}

		return commands[message.text]?.execute(message) ?: throw BotCommandNotFoundException(
			message.text,
			message.chat.id.toString()
		)
	}
}