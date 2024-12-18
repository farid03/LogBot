package com.vk.logbot.client.core.di

import com.vk.logbot.client.core.ktor.HttpClientFactory
import org.koin.dsl.module

val ktorModule = module {
    single {
        HttpClientFactory()
    }
}