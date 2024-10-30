package com.vk.logbot.server.models

data class Config(
    val id: Int,
    val userId: Int,
    val name: String,
    val regExp: String
)