package com.vk.logbot.webjmix.service

interface SessionDataProvider {

    fun getCurrentTelegramId(): Long?

    fun setCurrentTelegramId(telegramId: Long)

    fun getCurrentConfigId(): Long?

    fun setCurrentConfigId(configId: Long)
}