package com.vk.logbot.web.model

import kotlinx.serialization.Serializable

@Serializable
data class ConfigFile(
    val id: String,
    val name: String,
    val message: String,
    val regular: String
)