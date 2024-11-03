package com.vk.logbot.bot.exception

class BotCommandNotFoundException(command: String, chatId: Long) : BotException(
    "Команда \"$command\" не найдена", "Команда не найдена", chatId
)