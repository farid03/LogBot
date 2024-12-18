package com.vk.logbot.client.domain.usecases.config

import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.data.model.UserInfo
import com.vk.logbot.client.domain.repository.api.IConfigRepository

internal class GetAllConfigUseCase(private val repository: IConfigRepository) : IGetAllConfigUseCase {
    override suspend fun get(userInfo: UserInfo) = repository.getAllConfig(userInfo)

    override suspend fun get(userId: String, companyId: String) =
        repository.getAllConfig(UserInfo(telegramId = userId, idCompany = companyId))

    override suspend operator fun invoke(userInfo: UserInfo) = get(userInfo)
    override suspend operator fun invoke(userId: String, companyId: String) = get(userId = userId, companyId = companyId)
}

interface IGetAllConfigUseCase {
    suspend operator fun invoke(userInfo: UserInfo): Result<List<ConfigFile>>
    suspend fun get(userInfo: UserInfo): Result<List<ConfigFile>>
    suspend operator fun invoke(userId: String, companyId: String):Result<List<ConfigFile>>
    suspend fun get(userId: String, companyId: String):Result<List<ConfigFile>>
}