package com.vk.logbot.client.domain.usecases.config

import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.domain.repository.api.IConfigRepository

internal class UpdateConfigUseCase(private val repository: IConfigRepository) : IUpdateConfigUseCase {
    override suspend fun update(config: ConfigFile): Result<String> {
        return repository.update(config)
    }
    override suspend operator fun invoke(config: ConfigFile) = update(config)
}

interface IUpdateConfigUseCase {
    suspend operator fun invoke(config: ConfigFile): Result<String> ///TODO ИЗМЕНИТЬ ТИП
    suspend fun update(config: ConfigFile): Result<String> ///TODO ИЗМЕНИТЬ ТИП
}