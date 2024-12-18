package com.vk.logbot.client.core.ktor.interceptor

import com.vk.logbot.client.core.net.ApiErrorResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import kotlinx.serialization.SerializationException

suspend fun ResponseException.isInvalidTokenError(): Boolean =
    try {
        val res = response.body<ApiErrorResponse>()
        res.errorCode == ApiErrorResponse.InvalidTokenCode
    } catch (e: SerializationException) {
        false
    }