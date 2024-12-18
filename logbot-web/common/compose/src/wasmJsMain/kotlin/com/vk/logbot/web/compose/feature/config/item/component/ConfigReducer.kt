package com.vk.logbot.web.compose.feature.config.item.component

import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.web.compose.mvi.component.Reducer
import com.vk.logbot.web.compose.feature.config.item.component.contract.ConfigAction
import com.vk.logbot.web.compose.feature.config.item.component.contract.ConfigState
import org.koin.core.annotation.Single

@Single
class ConfigReducer : Reducer<ConfigAction, ConfigState> {
    override fun reduce(action: ConfigAction, state: ConfigState): ConfigState {

        return when (action) {
            is ConfigAction.OnError -> {
                state.copy(errorMessage = action.message)
            }

            is ConfigAction.InitData -> {
                state.copy(
                    isCreating = action.configFile == null,
                    configFile = (action.configFile ?: ConfigFile.EMPTY).copy(userId = action.userInfo.telegramId),
                )
            }

            is ConfigAction.UpdateMessage -> {
                state.copy(
                    configFile = state.configFile.copy(
                        message = action.message
                    )
                )
            }

            is ConfigAction.UpdateName -> {
                state.copy(
                    configFile = state.configFile
                        .copy(name = action.name)
                )
            }

            is ConfigAction.UpdateRegular -> {
                state.copy(
                    configFile = state.configFile
                        .copy(regular = action.regular)
                )
            }
        }
    }
}