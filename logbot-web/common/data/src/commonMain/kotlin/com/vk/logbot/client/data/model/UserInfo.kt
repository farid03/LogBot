package com.vk.logbot.client.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val telegramId: String,
    val idCompany: String
)


