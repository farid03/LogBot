package com.vk.logbot.web.feature.splash.component.contract

import com.vk.logbot.web.core.mvi.contract.MviIntent

sealed class SplashIntent: MviIntent {
    data class OnLoad(val isLoad: Boolean) : SplashIntent()
}