package com.vk.logbot.client.domain.repository.impl

import com.vk.logbot.client.data.model.UserInfo
import com.vk.logbot.client.domain.repository.api.IUserRepository
import com.vk.logbot.client.domain.sevices.api.IUserServices
import io.ktor.client.call.*

internal class UserRepository(private val services: IUserServices) : IUserRepository {
    override suspend fun logout(user: UserInfo): Result<String> {
        return try {
            val response = services.logout(user)
            ////
            ///
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)

        }
    }

    override suspend fun auth(user: UserInfo): Result<String> {
        return try {
            val response = services.logout(user)
            // TODO(" вернуть модель после сериализации и маппинга ")
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)

        }
    }
}