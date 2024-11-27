package com.vk.logbot.web.feature.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.Value
import com.vk.logbot.web.feature.main.contract.MainIntent
import com.vk.logbot.web.feature.main.contract.MainNavigation
import com.vk.logbot.web.feature.main.contract.MainState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainComponent(
    componentContext: ComponentContext,
    private val navigateConfigFiles: () -> Unit,
) : IMainComponent, ComponentContext by componentContext {


    private val feature = MainFeature().apply {
        navigation.onEach {
            when (it) {
                is MainNavigation.ShowMain -> navigateToConfigFiles()
            }
        }
    }
    override val state: Value<MainState> = feature.state
    override fun navigateToConfigFiles() = navigateConfigFiles()


    private fun fakeload() {
        coroutineScope.launch {
            delay(1000)
            feature.onIntent(MainIntent.OnLoad(true))
        }
    }

    @Composable
    override fun Render() {
        val state = state.subscribeAsState().value
        LaunchedEffect(Unit) { fakeload() }
        Box(modifier = Modifier.fillMaxSize()) {
            Text("MAIN")
        }
    }

}