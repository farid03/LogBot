package com.vk.logbot.commons.models

data class BotMessage(
    val message: String,
    val userIds: List<Long>
)