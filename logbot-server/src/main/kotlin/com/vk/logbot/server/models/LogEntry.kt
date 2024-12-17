package com.vk.logbot.server.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.ZonedDateTime

@Entity
@Table(name = "log_entries")
data class LogEntry(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "message")
    var message: String,
    @Column(name = "received_at")
    var receivedAt: ZonedDateTime = ZonedDateTime.now()
)