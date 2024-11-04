package com.vk.logbot.server.models.modelsDto


data class CreatingConfigDto(
    val userId: Long,
    val name: String,
    val regExp: String
)