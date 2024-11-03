package com.vk.logbot.bot.exception

class BotConfigFileNotValidException(chatId: Long) :
    BotException("Конфигурационный файл невалиден", "Конфигурационный файл невалиден! Попробуйте снова", chatId)