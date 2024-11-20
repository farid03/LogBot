package com.vk.logbot.server.models

import jakarta.persistence.*

@Entity
@Table(name = "configs")
data class Config(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "user_id")
    val userId: Long,
    val name: String,
    @Column(name = "reg_exp")
    val regExp: String
)