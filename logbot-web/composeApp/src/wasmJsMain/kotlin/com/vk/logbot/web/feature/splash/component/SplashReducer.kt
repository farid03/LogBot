package com.vk.logbot.web.feature.splash.component

import com.vk.logbot.web.core.mvi.component.Reducer
import com.vk.logbot.web.feature.splash.component.contract.SplashAction
import com.vk.logbot.web.feature.splash.component.contract.SplashState
import org.koin.core.annotation.Single

@Single
class SplashReducer : Reducer<SplashAction, SplashState> {


    override fun reduce(
        action: SplashAction,
        state: SplashState
    ): SplashState {
        return when (action) {
            is SplashAction.OnLoad -> state.copy(isLoading = action.isLoad)
        }
    }
}