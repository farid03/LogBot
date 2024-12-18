package com.vk.logbot.server.services

import com.vk.logbot.commons.models.BotMessage
import com.vk.logbot.server.models.LogEntry
import com.vk.logbot.server.repositories.LogEntryRepository
import mu.KLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class LogEntryKafkaService(
    private val kafkaTemplate: KafkaTemplate<String, BotMessage>,
    private val logEntryRepository: LogEntryRepository
) {
    companion object : KLogging()
    private val logger = KLogging().logger
    private val TOPIC = "bot-messages"

    @KafkaListener(topics = ["ser-messages"], groupId = "my-consumer-group")
    fun listenMessage(message: String) {
        logger.info { "Received message: $message" }
        logEntryRepository.save(LogEntry(message = message))
    }

    fun sendMessage(message: BotMessage) {
        logger.info { "Send message: $message" }
        kafkaTemplate.send(TOPIC, message)
    }
}