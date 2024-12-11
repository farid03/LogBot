package org.itmo.logbotauth.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "auth_sessions")
data class AuthSession(
    @Id
    @Column(name = "telegram_id")
    var telegramId: Long,

    @Column(name = "auth_code")
    var authCode: String,

    @Column(name = "authenticated")
    var authenticated: Boolean = false
) {
    constructor() : this(telegramId = 0, authCode = "")
}
