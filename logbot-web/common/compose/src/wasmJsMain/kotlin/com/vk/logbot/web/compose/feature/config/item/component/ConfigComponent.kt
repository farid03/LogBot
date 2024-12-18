package com.vk.logbot.web.compose.feature.config.item.component

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.Value
import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.data.model.UserInfo
import com.vk.logbot.web.compose.feature.config.item.component.contract.ConfigIntent
import com.vk.logbot.web.feature.config.item.component.contract.ConfigNavigation
import com.vk.logbot.web.compose.feature.config.item.component.contract.ConfigState
import com.vk.logbot.web.compose.feature.config.item.ui.ConfigScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ConfigComponent(
    componentContext: ComponentContext,
    userInfo: UserInfo,
    configFile: ConfigFile? = null,
    navigateToMainScreen: () -> Unit,
    backToPreviousScreen: () -> Unit,
) : IConfigComponent, ComponentContext by componentContext {
    private val feature = ConfigFeature().apply {
        navigation.onEach {
            when (it) {
                is ConfigNavigation.Main -> navigateToMainScreen()
                is ConfigNavigation.Back -> backToPreviousScreen()
            }
        }.launchIn(coroutineScope)
    }

    init {
        feature.onIntent(ConfigIntent.InitData(userInfo, configFile))

    }

    override val state: Value<ConfigState> = feature.state

    override fun updateName(name: String) {
        feature.onIntent(ConfigIntent.UpdateName(name))
    }

    override fun updateRegular(regular: String) {
        feature.onIntent(ConfigIntent.UpdateRegular(regular))
    }

    override fun updateMessage(message: String) {
        feature.onIntent(ConfigIntent.UpdateMessage(message))
    }

    override fun deleteConfig() {
        feature.onIntent(ConfigIntent.Delete)
    }

    override fun save() {
        feature.onIntent(ConfigIntent.Save)
    }


    @Composable
    override fun Render() {
        val state = state.subscribeAsState().value
        ConfigScreen(state = state, toDelegate())
    }
}