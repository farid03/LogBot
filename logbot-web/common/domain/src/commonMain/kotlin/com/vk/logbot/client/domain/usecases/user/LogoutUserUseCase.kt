package com.vk.logbot.client.domain.usecases.user

import com.vk.logbot.client.data.model.UserInfo
import com.vk.logbot.client.domain.repository.api.IUserRepository

internal class LogoutUserUseCase(private val repository: IUserRepository) : ILogoutUserUseCase {
    override suspend fun logout(userId: String, companyId: String): Result<String> {
        return repository.logout(UserInfo(telegramId = userId, idCompany = companyId))
    }

    override suspend fun logout(userInfo: UserInfo): Result<String> {
        return repository.logout(userInfo)
    }

    suspend operator fun invoke(userId: String, companyId: String) = logout(userId = userId, companyId = companyId)
    suspend operator fun invoke(userInfo: UserInfo) = logout(userInfo)
}

interface ILogoutUserUseCase {
    suspend fun logout(userId: String, companyId: String): Result<String>
    suspend fun logout(userInfo: UserInfo): Result<String>
}