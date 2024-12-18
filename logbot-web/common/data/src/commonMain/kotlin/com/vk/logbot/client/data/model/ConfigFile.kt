package com.vk.logbot.client.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigFile(
    @SerialName("id") val id: Long = 0,
    @SerialName("userId") val userId: String,
    @SerialName("name") val name: String,
    @SerialName("message") val message: String,
    @SerialName("regExp") val regular: String,
    @SerialName("active") val active: Boolean,

    ) {
    companion object {
        val EMPTY = ConfigFile(-1, "", "", "", "", false)
    }
}