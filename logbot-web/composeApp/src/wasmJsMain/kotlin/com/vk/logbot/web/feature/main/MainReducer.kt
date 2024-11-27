package com.vk.logbot.web.feature.main

import com.vk.logbot.web.core.mvi.component.Reducer
import com.vk.logbot.web.feature.main.contract.MainAction
import com.vk.logbot.web.feature.main.contract.MainState
import org.koin.core.annotation.Single

@Single
class MainReducer : Reducer<MainAction, MainState> {
    override fun reduce(
        action: MainAction,
        state: MainState
    ): MainState {
        return when (action) {
            is MainAction.OnLoad ->
                state.copy(isLoading = action.isLoad)
        }
    }
}