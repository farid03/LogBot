package com.vk.logbot.client.core.net

interface BaseResponse {
    val success: Boolean
    val message: String?
}