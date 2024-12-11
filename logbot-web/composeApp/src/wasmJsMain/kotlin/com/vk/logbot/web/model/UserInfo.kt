package com.vk.logbot.web.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val telegramId: Int,
    val idCompany: String
)