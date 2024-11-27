package com.vk.logbot.web.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val telegramId: Long,
    val idCompany: String
)