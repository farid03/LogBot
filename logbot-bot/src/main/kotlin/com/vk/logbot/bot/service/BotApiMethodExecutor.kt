package com.vk.logbot.bot.service

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import java.io.Serializable

/**
 * Исполнитель методов Telegram Bot API.
 */
interface BotApiMethodExecutor {

    /**
     * Исполняет метод Telegram Bot API.
     */
    fun <T : Serializable, Method : BotApiMethod<T>> executeBotApiMethod(method: Method)
}