package com.vk.logbot.web.compose.feature.config.item.component

import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.data.model.UserInfo
import com.vk.logbot.client.domain.usecases.config.ICreateConfigUseCase
import com.vk.logbot.client.domain.usecases.config.IDeleteConfigUseCase
import com.vk.logbot.client.domain.usecases.config.IUpdateConfigUseCase
import com.vk.logbot.web.compose.mvi.component.BaseFeature
import com.vk.logbot.web.compose.feature.config.item.component.contract.ConfigAction
import com.vk.logbot.web.compose.feature.config.item.component.contract.ConfigIntent
import com.vk.logbot.web.compose.feature.config.item.component.contract.ConfigState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ConfigFeature : BaseFeature<ConfigState, ConfigIntent, ConfigAction, ConfigReducer>(), KoinComponent {
    override fun initState(): ConfigState = ConfigState()
    private val createConfigUseCase: ICreateConfigUseCase by inject()
    private val updateConfigUseCase: IUpdateConfigUseCase by inject()
    private val deleteConfigUseCase: IDeleteConfigUseCase by inject()
    override val reducer: ConfigReducer by inject<ConfigReducer>()
    override fun handleIntent(intent: ConfigIntent) {
        when (intent) {
            is ConfigIntent.UpdateMessage -> updateMessage(intent.message)
            is ConfigIntent.UpdateName -> updateName(intent.name)
            is ConfigIntent.UpdateRegular -> updateRegular(intent.regular)
            is ConfigIntent.InitData -> initData(intent.userInfo,intent.config)
            ConfigIntent.Delete -> delete()
            ConfigIntent.Save -> save()
        }
    }

    private fun delete() {
        lifecycleScope.launch {
            deleteConfigUseCase(state.value.configFile)
        }
    }

    private fun save() {
        lifecycleScope.launch {
            if (state.value.isCreating)
                createConfigUseCase(state.value.configFile)
            else updateConfigUseCase(state.value.configFile)
        }
    }

    private fun initData(userInfo: UserInfo, configFile: ConfigFile?) {
        onAction(ConfigAction.InitData(userInfo,configFile))
    }

    private fun updateName(name: String) {
        onAction(ConfigAction.UpdateName(name))
    }

    private fun updateMessage(message: String) {
        onAction(ConfigAction.UpdateMessage(message))
    }

    private fun updateRegular(regular: String) {
        onAction(ConfigAction.UpdateRegular(regular))
    }
}