package com.vk.logbot.serverrestclient

import com.vk.logbot.commons.dto.ConfigDto
import com.vk.logbot.commons.dto.CreatingConfigDto
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity

class ServerClient(
    private val serverUrl: String,
    private val restClient: RestClient = RestClient.create()
) {
    fun getConfigsByUserId(userId: Long): List<ConfigDto> {
        return restClient.get()
            .uri {
                it.path("$serverUrl/configs")
                    .queryParam("user_id", userId)
                    .build()
            }
            .retrieve()
            .toEntity<List<ConfigDto>>()
            .body ?: emptyList()
    }

    fun createConfig(userId: Long, configName: String, configRegExp: String, configMessage: String): ConfigDto {
        return restClient.post()
            .uri("$serverUrl/configs")
            .body(CreatingConfigDto(userId, configName, configRegExp, configMessage))
            .retrieve()
            .toEntity<ConfigDto>()
            .body!!
    }
}