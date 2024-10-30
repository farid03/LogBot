package com.vk.logbot.server.models.modelsDto

import com.fasterxml.jackson.annotation.JsonProperty

data class EditConfigDto(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("regExp")
    val regExp: String
)