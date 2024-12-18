package com.vk.logbot.server.configs

import com.vk.logbot.commons.models.BotMessage
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
@EnableKafka
class KafkaConfig {

    @Bean
    fun producerFactory(): ProducerFactory<String, BotMessage> {
        val producerProps = hashMapOf<String, Any>()
        producerProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        producerProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java


        return DefaultKafkaProducerFactory(producerProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, BotMessage> {
        return KafkaTemplate(producerFactory())
    }
}