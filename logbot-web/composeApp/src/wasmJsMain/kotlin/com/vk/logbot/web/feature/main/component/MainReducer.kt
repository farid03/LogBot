package com.vk.logbot.web.feature.main.component

import com.vk.logbot.web.core.mvi.component.Reducer
import com.vk.logbot.web.feature.main.component.contract.MainAction
import com.vk.logbot.web.feature.main.component.contract.MainState
import org.koin.core.annotation.Single

@Single
class MainReducer : Reducer<MainAction, MainState> {
    override fun reduce(
        action: MainAction,
        state: MainState
    ): MainState {
        return when (action) {
            is MainAction.SetUser ->
                state.copy(user = action.user)
        }
    }
}