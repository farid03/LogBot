package com.vk.logbot.bot.exception

class BotCommandNotFoundException(command: String, chatId: String) : BotException(
    "Команда \"$command\" не найдена", "Команда не найдена", chatId
)