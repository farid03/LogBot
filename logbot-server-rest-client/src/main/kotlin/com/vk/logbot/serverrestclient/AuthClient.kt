package com.vk.logbot.serverrestclient

import com.vk.logbot.commons.dto.auth.AuthDto
import com.vk.logbot.commons.dto.auth.AuthStateDto
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity

class AuthClient(
    private val authUrl: String,
    private val restClient: RestClient = RestClient.create()
) {
    fun authTelegramUser(telegramId: Long, authCode: String): Boolean {
        return restClient.post()
            .uri("$authUrl/auth")
            .body(AuthDto(telegramId, authCode))
            .retrieve()
            .toEntity<AuthStateDto>()
            .body?.authenticated ?: false
    }
}