package com.vk.logbot.bot.repository

import com.vk.logbot.bot.model.entity.ChatInfo
import org.springframework.data.repository.CrudRepository

/**
 * Репозиторий информации о чатах.
 */
interface ChatInfoRepository : CrudRepository<ChatInfo, Long>