package com.vk.logbot.client.domain.repository.api

import com.vk.logbot.client.data.model.UserInfo

interface IUserRepository {
    suspend fun logout(user: UserInfo): Result<String> ///TODO ИЗМЕНИТЬ ТИП
    suspend fun auth(user: UserInfo): Result<String> ///TODO ИЗМЕНИТЬ ТИП
}