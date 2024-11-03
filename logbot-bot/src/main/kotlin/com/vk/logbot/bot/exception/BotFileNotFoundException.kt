package com.vk.logbot.bot.exception

class BotFileNotFoundException(fileId: String, chatId: Long) : BotException(
    "Файл с id \"$fileId\" не найден", "Файл не найден", chatId
)