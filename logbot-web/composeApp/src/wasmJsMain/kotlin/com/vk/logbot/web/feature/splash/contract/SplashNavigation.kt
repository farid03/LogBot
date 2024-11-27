package com.vk.logbot.web.feature.splash.contract

import com.vk.logbot.web.core.mvi.contract.Navigation

sealed class SplashNavigation: Navigation {
    data object ShowMain: SplashNavigation()
}