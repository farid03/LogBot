package com.vk.logbot.webjmix.config

import com.vk.logbot.serverrestclient.AuthClient
import com.vk.logbot.serverrestclient.ServerClient
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
@ConfigurationProperties(prefix = "external-services")
class ExternalServicesConfig {

    lateinit var authUrl: String
    lateinit var serverUrl: String

    @Bean
    fun restClient(): RestClient {
        return RestClient.create()
    }

    @Bean
    fun authClient(): AuthClient {
        return AuthClient(authUrl, restClient())
    }

    @Bean
    fun serverClient(): ServerClient {
        return ServerClient(serverUrl, restClient())
    }
}