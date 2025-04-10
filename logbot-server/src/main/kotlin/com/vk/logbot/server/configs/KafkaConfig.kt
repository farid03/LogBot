package com.vk.logbot.server.configs

import com.vk.logbot.commons.models.BotMessage
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.*
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
@EnableKafka
class KafkaConfig {

    @Value("\${spring.kafka.bootstrap-servers}")
    private val kafkaUrl: String = "localhost:9093";

    @Bean
    fun producerFactory(): ProducerFactory<String, BotMessage> {
        val producerProps = hashMapOf<String, Any>()
        producerProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaUrl
        producerProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        producerProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java


        return DefaultKafkaProducerFactory(producerProps)
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, BotMessage> {
        val consumerProps = hashMapOf<String, Any>()
        consumerProps[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaUrl
        consumerProps[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"

        return DefaultKafkaConsumerFactory(consumerProps);
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, BotMessage> {
        return KafkaTemplate(producerFactory())
    }
}