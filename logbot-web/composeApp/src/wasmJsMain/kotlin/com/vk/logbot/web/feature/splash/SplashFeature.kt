package com.vk.logbot.web.feature.splash

import com.vk.logbot.web.core.mvi.component.BaseFeature
import com.vk.logbot.web.feature.splash.contract.SplashAction
import com.vk.logbot.web.feature.splash.contract.SplashIntent
import com.vk.logbot.web.feature.splash.contract.SplashState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashFeature() : BaseFeature<SplashState, SplashIntent, SplashAction, SplashReducer>(),
    KoinComponent {
    override fun initState(): SplashState = SplashState()
    override val reducer: SplashReducer by inject<SplashReducer>()

    override fun handleIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.OnLoad -> onAction(SplashAction.OnLoad(intent.isLoad))
        }
    }
}