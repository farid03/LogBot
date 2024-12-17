package com.vk.logbot.server.models

import jakarta.persistence.*

@Entity
@Table(name = "configs")
data class Config(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "user_id")
    val userId: Long = 0,
    @Column(name = "name")
    var name: String = "name",
    @Column(name = "reg_exp")
    var regExp: String = "reg_exp",
    @Column(name = "message")
    var message: String = "message",
    @Column(name = "active")
    var active : Boolean = false
)