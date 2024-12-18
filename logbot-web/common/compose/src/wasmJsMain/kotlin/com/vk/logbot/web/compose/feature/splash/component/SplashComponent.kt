package com.vk.logbot.web.feature.splash.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.Value
import com.vk.logbot.web.compose.feature.splash.component.ISplashComponent
import com.vk.logbot.web.compose.feature.splash.component.SplashFeature
import com.vk.logbot.web.compose.feature.splash.component.contract.SplashIntent
import com.vk.logbot.web.feature.splash.component.contract.SplashNavigation
import com.vk.logbot.web.feature.splash.component.contract.SplashState
import com.vk.logbot.web.feature.splash.ui.SplashScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SplashComponent(
    componentContext: ComponentContext,
    private val navigateMain: () -> Unit,
) : ISplashComponent, ComponentContext by componentContext {


    private val feature = SplashFeature().apply {
        navigation.onEach {
            when (it) {
                is SplashNavigation.ShowMain -> navigateToMain()
            }
        }.launchIn(coroutineScope)
    }
    override val state: Value<SplashState> = feature.state
    override fun navigateToMain() = navigateMain()


    private fun fakeload() {
        coroutineScope.launch {
            delay(2000)
            feature.onIntent(SplashIntent.OnLoad(false))
        }
    }

    @Composable
    override fun Render() {
        val state = state.subscribeAsState().value
        LaunchedEffect(Unit) { fakeload() }
        SplashScreen(state)
    }

}