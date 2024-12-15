package com.vk.logbot.bot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestTemplate

/**
 * Веб-конфигурация.
 */
@Configuration
class WebConfig {

    /**
     * Возвращает RestTemplate.
     */
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    /**
     * Возвращает RestClient.
     */
    @Bean
    fun restClient(): RestClient {
        return RestClient.create()
    }
}