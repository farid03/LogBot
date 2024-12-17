package com.vk.logbot.bot.service

import com.vk.logbot.bot.model.entity.ChatInfo

/**
 * Сервис для работы с информацией о чатах.
 */
interface ChatInfoService {

    /**
     * Возвращает информацию о чате по его id.
     */
    fun getChatInfoByChatId(chatId: Long): ChatInfo?

    /**
     * Создаёт новую информацию о чате.
     */
    fun createNewChatInfo(chatId: Long, userId: Long): ChatInfo

    /**
     * Обновляет название последнего состояния в информации о чате.
     */
    fun updateChatInfoLastState(chatId: Long, lastStateName: String)

    /**
     * Возвращает id пользователя, с которым бот находится в чате.
     */
    fun getUserIdByChatId(chatId: Long): Long?

    /**
     * Обновляет флаг авторизации.
     */
    fun updateChatInfoIsAuthorized(chatId: Long, isAuthorized: Boolean)
}