package com.vk.logbot.client.domain.sevices.impl

import com.vk.logbot.client.core.ktor.HttpClientFactory
import com.vk.logbot.client.data.model.UserInfo
import com.vk.logbot.client.domain.sevices.api.IUserServices
import io.ktor.client.request.*

internal class UserServices(clientFactory: HttpClientFactory) : IUserServices {
    private val client = clientFactory.rest()
    override suspend fun logout(userRequest: UserInfo) = client.post("/user") {
        setBody(userRequest)
    }

    override suspend fun auth(userRequest:UserInfo) = client.post("/user") {
        setBody(userRequest)
    }
}