package org.itmo.logbotauth.repositories

import org.itmo.logbotauth.models.AuthSession
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthSessionRepository: JpaRepository<AuthSession, Long> {
}