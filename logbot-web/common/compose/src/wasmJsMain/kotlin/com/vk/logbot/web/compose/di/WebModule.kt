package com.vk.logbot.web.compose.di

import com.vk.logbot.web.compose.feature.config.item.component.ConfigReducer
import com.vk.logbot.web.compose.feature.config.list.component.ListConfigReducer
import com.vk.logbot.web.compose.feature.main.component.MainReducer
import com.vk.logbot.web.compose.feature.not_tg.component.NotTgReducer
import com.vk.logbot.web.compose.feature.splash.component.SplashReducer
import org.koin.dsl.module

val webModule = module {
    single { NotTgReducer() }
    single { ListConfigReducer() }
    single { ConfigReducer() }
    single<MainReducer> {
        MainReducer()
    }
    single<SplashReducer> {
        SplashReducer()
    }
}

