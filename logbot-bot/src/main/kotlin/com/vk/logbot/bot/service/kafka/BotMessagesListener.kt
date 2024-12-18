package com.vk.logbot.bot.service.kafka

import com.vk.logbot.bot.service.BotApiMethodExecutor
import com.vk.logbot.bot.service.ChatInfoService
import com.vk.logbot.commons.models.BotMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

/**
 * Листенер сообщений для бота.
 */
@Service
class BotMessagesListener(
    private val botApiMethodExecutor: BotApiMethodExecutor,
    private val chatInfoService: ChatInfoService
) {
    /**
     * Прослушивает топик сообщений для бота и отправляет пользователям сообщения.
     */
    @KafkaListener(topics = ["bot-messages"])
    fun listenBotMessages(botMessage: BotMessage) {
        val userIds = botMessage.userIds
        userIds.forEach { userId ->
            val chatId = chatInfoService.getChatIdByUserId(userId)
            if (chatId != null) {
                val sendMessage = SendMessage(chatId.toString(), "\uD83D\uDEA8 Пришло сообщение:\nbotMessage.message")
                sendMessage.enableMarkdownV2(true)
                botApiMethodExecutor.executeBotApiMethod(sendMessage)
            }
        }
    }
}