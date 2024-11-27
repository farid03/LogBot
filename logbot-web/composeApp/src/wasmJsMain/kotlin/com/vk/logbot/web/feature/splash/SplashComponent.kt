package com.vk.logbot.web.feature.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.Value
import com.vk.logbot.web.feature.splash.contract.SplashIntent
import com.vk.logbot.web.feature.splash.contract.SplashNavigation
import com.vk.logbot.web.feature.splash.contract.SplashState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
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
        }
    }
    override val state: Value<SplashState> = feature.state
    override fun navigateToMain() = navigateMain()


    private fun fakeload() {
        coroutineScope.launch {
            delay(1000)
            feature.onIntent(SplashIntent.OnLoad(true))
        }
    }

    @Composable
    override fun Render() {
        val state = state.subscribeAsState().value
        LaunchedEffect(Unit) { fakeload() }
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            AnimatedVisibility(
                state.isLoading
            ) {
                CircularProgressIndicator()
            }
        }
    }

}