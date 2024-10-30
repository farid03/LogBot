package com.vk.logbot.server.models

data class Config(
    val id: Long,
    val userId: Long,
    val name: String,
    val regExp: String
)