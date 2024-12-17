package com.vk.logbot.serverrestclient

import com.vk.logbot.commons.dto.ConfigDto
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
}