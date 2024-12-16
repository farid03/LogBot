package com.vk.logbot.web.di

import org.koin.dsl.module

val mainModule = module {
    includes(webModule)
}
