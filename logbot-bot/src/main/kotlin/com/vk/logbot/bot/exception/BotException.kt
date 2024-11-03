package com.vk.logbot.bot.exception

open class BotException(internalMessage: String, val publicMessage: String, val chatId: Long) :
    Exception(internalMessage)