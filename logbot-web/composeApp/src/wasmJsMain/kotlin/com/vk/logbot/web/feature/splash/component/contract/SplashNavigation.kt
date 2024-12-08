package com.vk.logbot.web.feature.splash.component.contract

import com.vk.logbot.web.core.mvi.contract.Navigation

sealed class SplashNavigation: Navigation {
    data object ShowMain: SplashNavigation()
}