package com.vk.logbot.bot.exception

class BotIllegalCommandException(command: String, chatId: Long) :
    BotException("Вызов несуществующей команды \"$command\"", "Нет такой команды", chatId)