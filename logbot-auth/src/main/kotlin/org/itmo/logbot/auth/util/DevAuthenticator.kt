package org.itmo.logbot.auth.util

import jakarta.annotation.PostConstruct
import org.itmo.logbot.auth.services.AuthService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("dev")
class DevAuthenticator(
    private val authService: AuthService
) {
    @Value("\${dev-telegram-id}")
    private val devTelegramId: Long? = null

    @PostConstruct
    fun authenticateDevTelegramId() {
        devTelegramId ?: return
        authService.authTelegramUser(devTelegramId, authService.correctAuthCode)
    }
}