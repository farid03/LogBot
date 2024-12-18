package com.vk.logbot.client.domain.sevices.api

import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.data.model.ConfigIdRequest
import com.vk.logbot.client.data.model.UserInfo
import io.ktor.client.statement.*

interface IConfigServices {
    suspend fun getAllConfig(userInfo:UserInfo): HttpResponse
    suspend fun getConfigById(
        configIdRequest:
        ConfigIdRequest
    ): HttpResponse

    suspend fun create(config: ConfigFile): HttpResponse
    suspend fun update(config: ConfigFile): HttpResponse
    suspend fun delete(config: ConfigFile): HttpResponse

}