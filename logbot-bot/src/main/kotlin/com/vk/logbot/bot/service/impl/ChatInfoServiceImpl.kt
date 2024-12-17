package com.vk.logbot.bot.service.impl

import com.vk.logbot.bot.model.entity.ChatInfo
import com.vk.logbot.bot.repository.ChatInfoRepository
import com.vk.logbot.bot.service.ChatInfoService
import org.springframework.stereotype.Service

@Service
class ChatInfoServiceImpl(private val chatInfoRepository: ChatInfoRepository) : ChatInfoService {

    override fun getChatInfoByChatId(chatId: Long): ChatInfo? {
        return chatInfoRepository.findById(chatId).orElse(null)
    }

    override fun createNewChatInfo(chatId: Long, userId: Long): ChatInfo {
        return chatInfoRepository.save(ChatInfo(chatId, userId, null, false))
    }

    override fun updateChatInfoLastState(chatId: Long, lastStateName: String) {
        val chatInfo = chatInfoRepository.findById(chatId).get()
        chatInfo.lastStateName = lastStateName
        chatInfoRepository.save(chatInfo)
    }

    override fun getUserIdByChatId(chatId: Long): Long? {
        return getChatInfoByChatId(chatId)?.userId
    }

    override fun getChatIdByUserId(userId: Long): Long? {
        return chatInfoRepository.findChatInfoByUserId(userId)?.chatId
    }

    override fun updateChatInfoIsAuthorized(chatId: Long, isAuthorized: Boolean) {
        val chatInfo = chatInfoRepository.findById(chatId).get()
        chatInfo.isAuthorized = isAuthorized
        chatInfoRepository.save(chatInfo)
    }
}