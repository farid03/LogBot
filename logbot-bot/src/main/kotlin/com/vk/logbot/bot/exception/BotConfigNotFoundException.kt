package com.vk.logbot.bot.exception

class BotConfigNotFoundException(configName: String, chatId: Long) : BotException(
    "Конфигурация \"$configName\" не найдена", "Конфигурация не найдена", chatId
)