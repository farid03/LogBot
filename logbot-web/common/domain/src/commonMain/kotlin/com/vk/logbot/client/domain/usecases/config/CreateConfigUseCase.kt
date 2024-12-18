package com.vk.logbot.client.domain.usecases.config

import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.domain.repository.api.IConfigRepository

internal class CreateConfigUseCase(private val repository: IConfigRepository) : ICreateConfigUseCase {
    override suspend fun create(config: ConfigFile): Result<String> {
        return repository.create(config)
    }

   override suspend operator fun invoke(config: ConfigFile) = create(config)
}

interface ICreateConfigUseCase {
    suspend operator fun invoke(config: ConfigFile): Result<String>
    suspend fun create(config: ConfigFile): Result<String> ///TODO ИЗМЕНИТЬ ТИП
}