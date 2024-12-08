package com.vk.logbot.web.feature.config.item.component

import com.vk.logbot.web.core.mvi.component.Reducer
import com.vk.logbot.web.feature.config.item.component.contract.ConfigAction
import com.vk.logbot.web.feature.config.item.component.contract.ConfigState

class ConfigReducer : Reducer<ConfigAction, ConfigState> {
    override fun reduce(action: ConfigAction, state: ConfigState): ConfigState {

        return when (action) {
            is ConfigAction.OnError -> {
                state.copy(errorMessage = action.message)
            }

            is ConfigAction.OnIsCreate -> {
                state.copy(isCreating = action.isCreate)
            }

            is ConfigAction.UpdateMessage -> {
                state.copy(messageConfig = action.message)
            }

            is ConfigAction.UpdateName -> {
                state.copy(nameConfig = action.name)
            }

            is ConfigAction.UpdateRegular -> {
                state.copy(regular = action.regular)
            }
        }
    }
}