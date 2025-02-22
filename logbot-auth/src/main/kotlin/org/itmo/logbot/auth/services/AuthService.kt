package org.itmo.logbot.auth.services

import org.itmo.logbot.auth.models.AuthSession
import org.itmo.logbot.auth.repositories.AuthSessionRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class AuthService(
    private val repository: AuthSessionRepository,
    @Value("\${logbot.auth.code}")
    val correctAuthCode: String
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

    fun isAuthenticated(telegramId: Long): Boolean {
        return repository.findById(telegramId).getOrNull()?.authenticated ?: false
    }
}