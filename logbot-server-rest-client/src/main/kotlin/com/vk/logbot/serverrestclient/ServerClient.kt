package com.vk.logbot.serverrestclient

import com.vk.logbot.commons.dto.ConfigDto
import com.vk.logbot.commons.dto.CreatingConfigDto
import com.vk.logbot.commons.dto.EditConfigDto
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity

class ServerClient(
    private val serverUrl: String,
    private val restClient: RestClient = RestClient.create()
) {
    fun getConfigById(configId: Long): ConfigDto {
        return restClient.get()
            .uri("$serverUrl/configs?id=$configId")
            .retrieve()
            .toEntity<ConfigDto>()
            .body!!
    }

    fun getConfigsByUserId(userId: Long): List<ConfigDto> {
        return restClient.get()
            .uri("$serverUrl/configs?user_id=$userId")
            .retrieve()
            .toEntity<List<ConfigDto>>()
            .body ?: emptyList()
    }

    fun createConfig(
        userId: Long,
        configName: String,
        configRegExp: String,
        configMessage: String
    ): ConfigDto {
        return restClient.post()
            .uri("$serverUrl/configs")
            .body(CreatingConfigDto(userId, configName, configRegExp, configMessage))
            .retrieve()
            .toEntity<ConfigDto>()
            .body!!
    }

    fun editConfig(
        configId: Long,
        configName: String,
        configRegExp: String,
        configMessage: String,
        configIsActive: Boolean
    ): ConfigDto {
        return restClient.put()
            .uri("$serverUrl/configs")
            .body(EditConfigDto(configId, configName, configRegExp, configMessage, configIsActive))
            .retrieve()
            .toEntity<ConfigDto>()
            .body!!
    }

    fun deleteConfig(configId: Long) {
        restClient.delete()
            .uri("$serverUrl/configs?id=$configId")
    }
}