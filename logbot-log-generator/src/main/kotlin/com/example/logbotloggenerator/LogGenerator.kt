package com.example.logbotloggenerator

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class LogGenerator(private val kafkaTemplate: KafkaTemplate<String, String>) {

    private val TOPIC = "ser-messages"

    private fun sendMessage(message: String) {
        println("Sending message: $message")
        kafkaTemplate.send(TOPIC, message)
    }

    @Scheduled(fixedRate = 5000) // Повторять каждые 5 секунд
    fun generateLog() {
        sendMessage("2024-12-11 12:00:00 DEBUG LogGenerator - Это сообщение для DEBUG уровня")
        sendMessage("2024-12-11 12:00:01 INFO  LogGenerator - Это сообщение для INFO уровня")
        sendMessage("2024-12-11 12:00:01 ERROR LogGenerator - Это информационное WARN уровня")
    }

}