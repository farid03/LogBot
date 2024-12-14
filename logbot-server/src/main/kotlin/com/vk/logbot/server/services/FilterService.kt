package com.vk.logbot.server.services

import com.vk.logbot.commons.models.BotMessage
import mu.KLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class FilterService(
    private val kafkaTemplate: KafkaTemplate<String, BotMessage>,
    private val configService: ConfigService
) {
    companion object : KLogging()
    private val logger = KLogging().logger
    private val TOPIC = "bot-messages"

    @KafkaListener(topics = ["ser-messages"])
    fun listenMessage(message: String) {
        logger.info { "Received message: $message" }
        filter(message)
    }

    fun sendMessage(message: BotMessage) {
        logger.info { "Send message: $message" }
        kafkaTemplate.send(TOPIC, message)
    }

    fun filter(message: String) {
        val configs = configService.getActiveConfigs();
        println(configs)
        for (config in configs) {
            val regexp = config.regExp.toRegex()
            if (regexp.matches(message)) {
                sendMessage(BotMessage(config.userId, config.message))
            }
            logger.error { "Не подошло: $regexp" }
        }
    }
}