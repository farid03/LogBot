package com.vk.logbot.server.models.modelsDto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreatingConfigDto(
    @JsonProperty("userId")
    val userId: Int,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("regExp")
    val regExp: String
)