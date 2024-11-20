package org.itmo.logbotauth.services

import org.itmo.logbotauth.models.AuthSession
import org.itmo.logbotauth.repositories.AuthSessionRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val repository: AuthSessionRepository,
    @Value("\${logbot.auth.code}")
    private val correctAuthCode: String
) {

    fun authTelegramUser(telegramId: Long, authCode: String): Boolean {
        if (authCode != correctAuthCode) return false
        repository.save(
            AuthSession(
                telegramId = telegramId,
                authCode = authCode,
                authenticated = true
            )
        )
        return true
    }
}