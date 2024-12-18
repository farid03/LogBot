package com.vk.logbot.client.domain.usecases.config

import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.domain.repository.api.IConfigRepository

internal class GetConfigByIdUseCase(private val repository: IConfigRepository) : IGetConfigByIdUseCase {
  override  suspend operator fun invoke(configId: String) = get(configId)
    override suspend fun get(configId: String): Result<ConfigFile> = repository.getConfigById(configId)
}

interface IGetConfigByIdUseCase {
    suspend operator fun invoke(configId: String): Result<ConfigFile>
    suspend fun get(configId: String): Result<ConfigFile>
}