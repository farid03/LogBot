package com.vk.logbot.web.compose.feature.config.list.component

import com.vk.logbot.web.compose.mvi.component.Reducer
import com.vk.logbot.web.compose.feature.config.list.component.contract.ListConfigAction
import com.vk.logbot.web.compose.feature.config.list.component.contract.ListConfigState
import org.koin.core.annotation.Single

@Single
class ListConfigReducer : Reducer<ListConfigAction, ListConfigState> {
    override fun reduce(action: ListConfigAction, state: ListConfigState): ListConfigState {
        return when (action) {
            is ListConfigAction.SetConfigs -> {
                state.copy(configs = action.configs)
            }
        }
    }
}