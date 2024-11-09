package com.vk.logbot.bot.model

import com.vk.logbot.bot.model.enm.CacheDataType

/**
 * Ключ кэша данных.
 */
data class CacheKey(
    /**
     * ID чата.
     */
    val chatId: Long,
    /**
     * Тип сохранённых данных.
     */
    val cacheDataType: CacheDataType
)
