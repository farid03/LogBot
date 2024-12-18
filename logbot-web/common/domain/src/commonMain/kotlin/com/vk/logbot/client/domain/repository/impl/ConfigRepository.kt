package com.vk.logbot.client.domain.repository.impl

import com.vk.logbot.client.domain.sevices.api.IConfigServices
import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.data.model.ConfigIdRequest
import com.vk.logbot.client.data.model.UserInfo
import com.vk.logbot.client.domain.repository.api.IConfigRepository
import io.ktor.client.call.*

internal class ConfigRepository(private val services: IConfigServices) : IConfigRepository {
    override suspend fun getAllConfig(userInfo: UserInfo): Result<List<ConfigFile>> {
        return try {
            val response = services.getAllConfig(userInfo)
            // TODO(" вернуть модель после сериализации и маппинга ")
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getConfigById(configId: String): Result<ConfigFile> {
        return try {
            val response = services.getConfigById(ConfigIdRequest(configId))
            // TODO(" вернуть модель после сериализации и маппинга ")
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)

        }
    }

    override suspend fun create(config: ConfigFile): Result<String> {
        return try {
            val response = services.create(config)
            // TODO(" вернуть модель после сериализации и маппинга")
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)

        }
    }

    override suspend fun update(config: ConfigFile): Result<String> {
        return try {
            val response = services.update(config)
            ///TODO(" вернуть модель после сериализации и маппинга")
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)

        }
    }

    override suspend fun delete(config: ConfigFile): Result<String> {
        return try {
            val response = services.delete(config)
            ///TODO(" вернуть модель после сериализации ")
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}