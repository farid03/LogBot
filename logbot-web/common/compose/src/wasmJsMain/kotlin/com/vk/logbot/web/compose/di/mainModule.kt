package com.vk.logbot.web.compose.di

import com.vk.logbot.client.domain.di.useCaseModule
import org.koin.dsl.module

val mainModule = module {
    includes(webModule)
    includes(useCaseModule)
}
