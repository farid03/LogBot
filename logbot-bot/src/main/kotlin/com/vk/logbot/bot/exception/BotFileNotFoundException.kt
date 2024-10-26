package com.vk.logbot.bot.exception

class BotFileNotFoundException(fileId: String, chatId: String) : BotException(
	"Файл с id \"$fileId\" не найден", "Файл не найден", chatId
)