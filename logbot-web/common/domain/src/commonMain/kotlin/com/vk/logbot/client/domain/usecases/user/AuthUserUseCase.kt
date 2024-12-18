package com.vk.logbot.client.domain.usecases.user

import com.vk.logbot.client.data.model.UserInfo
import com.vk.logbot.client.domain.repository.api.IUserRepository

internal class AuthUserUseCase(private val repository: IUserRepository) : IAuthUserUseCase {
    override suspend fun auth(userId: String, companyId: String): Result<String> {
        return repository.auth(UserInfo(telegramId = userId, idCompany = companyId))
    }

    override suspend fun auth(userInfo: UserInfo): Result<String> {
        return repository.auth(userInfo)
    }
    suspend operator fun invoke(userId: String, companyId: String) = auth(userId = userId, companyId = companyId)
    suspend operator fun invoke(userInfo: UserInfo) = auth(userInfo)

}

interface IAuthUserUseCase {
    suspend fun auth(userInfo: UserInfo): Result<String>
    suspend fun auth(userId: String, companyId: String): Result<String> ///TODO ИЗМЕНИТЬ ТИП
}