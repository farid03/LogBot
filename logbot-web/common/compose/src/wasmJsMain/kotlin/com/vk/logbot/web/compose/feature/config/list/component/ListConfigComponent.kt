package com.vk.logbot.web.compose.feature.config.list.component

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.Value
import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.data.model.UserInfo
import com.vk.logbot.web.compose.feature.config.list.component.contract.ListConfigIntent
import com.vk.logbot.web.compose.feature.config.list.component.contract.ListConfigNavigation
import com.vk.logbot.web.compose.feature.config.list.component.contract.ListConfigState
import com.vk.logbot.web.compose.feature.config.list.ui.ListConfigScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ListConfigComponent(
    componentContext: ComponentContext,
    userInfo: UserInfo,
    navigateToConfigFile: (ConfigFile) -> Unit,
    navigateBack: () -> Unit,
) : IListConfigComponent, ComponentContext by componentContext {


    private val feature = ListConfigFeature().apply {
        navigation.onEach {
            when (it) {
                is ListConfigNavigation.Config -> navigateToConfigFile(it.config)
                is ListConfigNavigation.Back -> navigateBack()

            }
        }.launchIn(coroutineScope)
    }


    override fun navigateToConfig(config: ConfigFile) {
        feature.onIntent(ListConfigIntent.ShowConfigFile(config))
    }

    override val state: Value<ListConfigState> = feature.state

    @Composable
    override fun Render() {
        val state = state.subscribeAsState().value
        ListConfigScreen(
            state = state,
            delegate = toDelegate()
        )
    }
}