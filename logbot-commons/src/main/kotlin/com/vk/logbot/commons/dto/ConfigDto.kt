package com.vk.logbot.commons.dto

data class ConfigDto(
    val id: Long = 0,
    val userId: Long = 0,
    val name: String = "name",
    val regExp: String = "regExp",
    val message: String = "message",
    val active: Boolean = false
)
