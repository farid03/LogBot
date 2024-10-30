package com.vk.logbot.server.models.modelsDto

import com.fasterxml.jackson.annotation.JsonProperty

data class ConfigDto(
    @JsonProperty("regExp")
    val regExp: String
)