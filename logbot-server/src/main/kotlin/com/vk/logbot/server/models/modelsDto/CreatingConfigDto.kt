package com.vk.logbot.server.models.modelsDto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreatingConfigDto(
    val userId: Long,
    val name: String,
    val regExp: String
)