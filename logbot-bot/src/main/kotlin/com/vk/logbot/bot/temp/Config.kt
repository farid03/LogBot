package com.vk.logbot.bot.temp

@Deprecated("Временный класс, удалить в будущем")
data class Config(
    var id: Int?,
    var ownerId: Long,
    var name: String,
    var regExp: String,
    var message: String
)