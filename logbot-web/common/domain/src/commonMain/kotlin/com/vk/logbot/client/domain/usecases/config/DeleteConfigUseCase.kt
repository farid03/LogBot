package com.vk.logbot.client.domain.usecases.config

import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.domain.repository.api.IConfigRepository

internal class DeleteConfigUseCase(private val repository: IConfigRepository) : IDeleteConfigUseCase {
    override suspend fun delete(config: ConfigFile): Result<String>  {
        return repository.delete(config)
    }
    override suspend operator fun invoke(config: ConfigFile) = delete(config)
}

interface IDeleteConfigUseCase {
    suspend operator fun invoke(config: ConfigFile): Result<String>
    suspend fun delete(config: ConfigFile): Result<String> ///TODO ИЗМЕНИТЬ ТИП
}