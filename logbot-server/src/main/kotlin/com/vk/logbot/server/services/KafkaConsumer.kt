package com.vk.logbot.server.services

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer {

    @KafkaListener(topics = ["messages"])
    fun consume(message: String) {
        println("Received message: $message")
    }
}