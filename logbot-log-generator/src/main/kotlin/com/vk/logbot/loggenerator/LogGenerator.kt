package com.vk.logbot.loggenerator

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class LogGenerator(private val kafkaTemplate: KafkaTemplate<String, String>) {

    private val TOPIC = "ser-messages"

    private fun sendMessage(message: String) {
        println("Sending message: $message")
        kafkaTemplate.send(TOPIC, message)
    }

    @Scheduled(fixedRate = 5000) // Повторять каждые 5 секунд
    fun generateLog() {
        sendMessage("${getCurrentTime()} DEBUG LogGenerator - Это сообщение DEBUG уровня")
        sendMessage("${getCurrentTime()} INFO  LogGenerator - Это сообщение INFO уровня")
        sendMessage("${getCurrentTime()} WARN LogGenerator - Это сooбщение WARN уровня")
        sendMessage("${getCurrentTime()} ERROR LogGenerator - Это сooбщение ERROR уровня")
    }

    private fun getCurrentTime() = Timestamp(System.currentTimeMillis())

}