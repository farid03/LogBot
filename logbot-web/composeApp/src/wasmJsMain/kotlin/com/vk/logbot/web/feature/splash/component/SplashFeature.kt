package com.vk.logbot.web.feature.splash.component

import com.vk.logbot.web.core.mvi.component.BaseFeature
import com.vk.logbot.web.feature.splash.component.contract.SplashAction
import com.vk.logbot.web.feature.splash.component.contract.SplashIntent
import com.vk.logbot.web.feature.splash.component.contract.SplashNavigation
import com.vk.logbot.web.feature.splash.component.contract.SplashState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashFeature : BaseFeature<SplashState, SplashIntent, SplashAction, SplashReducer>(),
    KoinComponent {
    override fun initState(): SplashState = SplashState()
    override val reducer: SplashReducer by inject<SplashReducer>()

    override fun handleIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.OnLoad -> load(intent.isLoad)
        }
    }

    fun load(isLoad: Boolean) {
        onAction(SplashAction.OnLoad(isLoad))
        if (!isLoad) {
            onNavigation(SplashNavigation.ShowMain)
        }
    }
}