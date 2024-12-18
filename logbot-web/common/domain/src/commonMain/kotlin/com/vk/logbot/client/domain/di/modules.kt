package com.vk.logbot.client.domain.di

import com.vk.logbot.client.core.di.ktorModule
import com.vk.logbot.client.domain.repository.api.IConfigRepository
import com.vk.logbot.client.domain.repository.api.IUserRepository
import com.vk.logbot.client.domain.repository.impl.ConfigRepository
import com.vk.logbot.client.domain.repository.impl.UserRepository
import com.vk.logbot.client.domain.sevices.api.IConfigServices
import com.vk.logbot.client.domain.sevices.api.IUserServices
import com.vk.logbot.client.domain.sevices.impl.ConfigServices
import com.vk.logbot.client.domain.sevices.impl.UserServices
import com.vk.logbot.client.domain.usecases.config.*
import com.vk.logbot.client.domain.usecases.user.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val servicesModule = module {
    includes(ktorModule)
    singleOf(::ConfigServices) {
        bind<IConfigServices>()
        createdAtStart()
    }
    singleOf(::UserServices) {
        bind<IUserServices>()
        createdAtStart()
    }
}
internal val repositoryModule = module {
    includes(servicesModule)
    singleOf(::UserRepository) {
        bind<IUserRepository>()
        createdAtStart()
    }
    singleOf(::ConfigRepository) {
        bind<IConfigRepository>()
        createdAtStart()
    }
}
val useCaseModule = module {
    includes(repositoryModule)
    factoryOf(::GetAllConfigUseCase) {
        bind<IGetAllConfigUseCase>()
        createdAtStart()
    }
    factoryOf(::GetConfigByIdUseCase) {
        bind<IGetConfigByIdUseCase>()
        createdAtStart()
    }
    factoryOf(::UpdateConfigUseCase) {
        bind<IUpdateConfigUseCase>()
        createdAtStart()
    }
    factoryOf(::DeleteConfigUseCase) {
        bind<IDeleteConfigUseCase>()
        createdAtStart()
    }
    factoryOf(::CreateConfigUseCase) {
        bind<ICreateConfigUseCase>()
        createdAtStart()
    }
    factoryOf(::CreateConfigUseCase) {
        bind<ICreateConfigUseCase>()
        createdAtStart()
    }
    factoryOf(::AuthUserUseCase) {
        bind<IAuthUserUseCase>()
        createdAtStart()
    }
    factoryOf(::LogoutUserUseCase) {
        bind<ILogoutUserUseCase>()
        createdAtStart()
    }
}