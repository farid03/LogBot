package com.vk.logbot.commons.dto

data class EditConfigDto(
    val id: Long,
    val name: String,
    val regExp: String,
    val message: String,
    val active: Boolean
)