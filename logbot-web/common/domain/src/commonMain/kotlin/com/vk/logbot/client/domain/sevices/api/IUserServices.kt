package com.vk.logbot.client.domain.sevices.api

import com.vk.logbot.client.data.model.UserInfo
import io.ktor.client.statement.*

interface IUserServices {
    suspend fun logout(userRequest: UserInfo): HttpResponse
    suspend fun auth(userRequest: UserInfo): HttpResponse
}