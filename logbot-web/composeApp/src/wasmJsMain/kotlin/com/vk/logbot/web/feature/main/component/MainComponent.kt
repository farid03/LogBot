package com.vk.logbot.web.feature.main.component

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.Value
import com.vk.logbot.web.feature.main.component.contract.MainNavigation
import com.vk.logbot.web.feature.main.component.contract.MainState
import com.vk.logbot.web.feature.main.ui.MainScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainComponent(
    componentContext: ComponentContext,
    navigateConfigFiles: () -> Unit,
    navigateCreateConfigFile: () -> Unit,
) : IMainComponent, ComponentContext by componentContext {


    private val feature = MainFeature().apply {
        navigation.onEach {
            when (it) {
                is MainNavigation.ShowConfigFiles -> navigateConfigFiles()
                is MainNavigation.ShowCreateConfigFile -> navigateCreateConfigFile()
            }
        }.launchIn(coroutineScope)
    }
    override val state: Value<MainState> = feature.state
    override fun navigateToConfigFiles() {
        feature.onNavigation(MainNavigation.ShowConfigFiles)
    }

    override fun navigateToCreateConfigFile() {
        feature.onNavigation(MainNavigation.ShowCreateConfigFile)
    }

    @Composable
    override fun Render() {
        val state = state.subscribeAsState().value
        MainScreen(
            state = state,
            delegate = toDelegate()
        )
    }

}