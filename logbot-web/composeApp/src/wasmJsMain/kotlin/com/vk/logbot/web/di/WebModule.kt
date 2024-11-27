package com.vk.logbot.web.di

import com.vk.logbot.web.feature.main.MainReducer
import com.vk.logbot.web.feature.splash.SplashReducer
import org.koin.dsl.module

val webModule = module {
    single<MainReducer> {
        MainReducer()
    }
    single<SplashReducer> {
        SplashReducer()
    }
}

