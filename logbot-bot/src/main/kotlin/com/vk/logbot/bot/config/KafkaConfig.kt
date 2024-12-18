package com.vk.logbot.bot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.converter.JsonMessageConverter
import org.springframework.kafka.support.converter.MessageConverter

@Configuration
class KafkaConfig {

    @Bean
    fun messageConverter(): MessageConverter {
        return JsonMessageConverter()
    }
}