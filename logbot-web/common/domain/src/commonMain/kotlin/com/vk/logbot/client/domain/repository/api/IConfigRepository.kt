package com.vk.logbot.client.domain.repository.api

import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.data.model.UserInfo

interface IConfigRepository {
    suspend fun getAllConfig(userInfo: UserInfo): Result<List<ConfigFile>>
    suspend fun getConfigById(configId: String): Result<ConfigFile>//TODO (поменять на модель)

    suspend fun create(config: ConfigFile): Result<String>//TODO (поменять на модель)
    suspend fun update(config: ConfigFile): Result<String>//TODO (поменять на модель)
    suspend fun delete(config: ConfigFile): Result<String>//TODO (поменять на модель)

}