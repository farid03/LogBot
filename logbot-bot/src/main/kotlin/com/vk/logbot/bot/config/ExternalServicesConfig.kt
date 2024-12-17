package com.vk.logbot.bot.config

import com.vk.logbot.serverrestclient.AuthClient
import com.vk.logbot.serverrestclient.ServerClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

/**
 * Конфигурация внешних сервисов.
 */
@Configuration
@ConfigurationProperties(prefix = "external-services")
class ExternalServicesConfig {

    /**
     * URL бэкенда.
     */
    lateinit var serverUrl: String

    /**
     * URL сервиса авторизации.
     */
    lateinit var authUrl: String

    /**
     * REST-клиент.
     */
    @Autowired
    lateinit var restClient: RestClient

    /**
     * Возвращает AuthClient.
     */
    @Bean
    fun authClient(): AuthClient {
        return AuthClient(authUrl, restClient)
    }

    /**
     * Возвращает ServerClient.
     */
    @Bean
    fun serverClient(): ServerClient {
        return ServerClient(serverUrl, restClient)
    }
}