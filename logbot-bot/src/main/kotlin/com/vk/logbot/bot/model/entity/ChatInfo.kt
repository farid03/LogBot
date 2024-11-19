package com.vk.logbot.bot.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

/**
 * Информация о чате.
 */
@Entity
data class ChatInfo(
    /**
     * Идентификатор чата.
     */
    @Id var chatId: Long,

    /**
     * Идентификатор пользователя, который находится в данном чате с ботом.
     */
    @Column(nullable = false, unique = true) var userId: Long,

    /**
     * Строковый идентификатор последнего (текущего) состояния бота в данном чате.
     */
    @Column var lastStateName: String?,

    /**
     * Флаг авторизации пользователя.
     */
    @Column var isAuthorized: Boolean
)