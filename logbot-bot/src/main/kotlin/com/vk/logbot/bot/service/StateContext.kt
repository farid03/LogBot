package com.vk.logbot.bot.service

import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Контекст состояний.
 */
interface StateContext {

    /**
     * Обрабатывает сообщение или коллбэк.
     */
    fun handleUpdate(update: Update)

    /**
     * Переключает состояние в указанном чате на состояние с указанным названием.
     */
    fun switchState(chatId: Long, stateName: String)

    /**
     * Переключает состояние в указанном чате на стартовое.
     */
    fun switchToStartState(chatId: Long)
}