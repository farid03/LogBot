package com.vk.logbot.client.domain.sevices.impl
import com.vk.logbot.client.domain.BuildKonfig
import com.vk.logbot.client.core.ktor.HttpClientFactory
import com.vk.logbot.client.domain.sevices.api.IConfigServices
import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.data.model.ConfigIdRequest
import com.vk.logbot.client.data.model.UserInfo
import io.ktor.client.request.*
import io.ktor.client.statement.*

internal class ConfigServices(clientFactory: HttpClientFactory) : IConfigServices {
    private val client = clientFactory.rest()
    private val baseUrl = "/configs"


    override suspend fun getAllConfig(userInfo: UserInfo): HttpResponse = client.get(baseUrl) {
        parameter("user_id", userInfo.telegramId)
    }


    override suspend fun getConfigById(
        configIdRequest: ConfigIdRequest
    ): HttpResponse = client.get {
        parameter("id", configIdRequest.id)
    }


    override suspend fun create(config: ConfigFile) = client.post(baseUrl) {
        setBody(config)
    }


    override suspend fun update(config: ConfigFile) = client.put(baseUrl) {
        setBody(config)
    }

    override suspend fun delete(config: ConfigFile) = client.delete(baseUrl) {
        parameter("id", config.id)
    }

}