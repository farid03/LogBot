package com.vk.logbot.bot.model

import com.vk.logbot.bot.model.enm.CallbackType

/**
 * Данные коллбэк-запроса.
 */
data class CallbackData(
    /**
     * Тип коллбэка.
     */
    val callbackType: CallbackType,
    /**
     * Данные коллбэка.
     */
    val data: String
)
