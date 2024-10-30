package com.vk.logbot.server.models.modelsDto

import com.fasterxml.jackson.annotation.JsonProperty

data class EditConfigDto(
    val id: Long,
    val name: String,
    val regExp: String
)