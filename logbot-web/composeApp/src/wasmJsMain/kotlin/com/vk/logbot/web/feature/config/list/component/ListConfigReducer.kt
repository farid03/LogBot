package com.vk.logbot.web.feature.config.list.component

import com.vk.logbot.web.core.mvi.component.Reducer
import com.vk.logbot.web.feature.config.list.component.contract.ListConfigAction
import com.vk.logbot.web.feature.config.list.component.contract.ListConfigState

class ListConfigReducer : Reducer<ListConfigAction, ListConfigState> {
    override fun reduce(action: ListConfigAction, state: ListConfigState): ListConfigState {
        return when (action) {
            is ListConfigAction.SetConfigs -> {
                state.copy(configs = action.configs)
            }
        }
    }
}