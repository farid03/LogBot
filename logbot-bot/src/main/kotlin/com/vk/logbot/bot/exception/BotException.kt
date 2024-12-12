package com.vk.logbot.bot.exception

/**
 * Исключение бота.
 */
open class BotException(

    /**
     * Внутреннее сообщение (предполагается, что это сообщение понадобится для отладки).
     */
    internalMessage: String,

    /**
     * Публичное сообщение (может быть отправлено в чат с пользователем).
     */
    val publicMessage: String
) : Exception(internalMessage)