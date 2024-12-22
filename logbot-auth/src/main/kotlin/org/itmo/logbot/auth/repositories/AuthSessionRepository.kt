package org.itmo.logbot.auth.repositories

import org.itmo.logbot.auth.models.AuthSession
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthSessionRepository: JpaRepository<AuthSession, Long> {
}