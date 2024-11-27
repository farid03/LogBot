package com.vk.logbot.web.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.vk.logbot.web.feature.main.IMainComponent
import com.vk.logbot.web.feature.splash.ISplashComponent
import kotlinx.serialization.Serializable

interface IRootComponent {
    val childStack: Value<ChildStack<*, Child>>
    fun onBackClicked()

    @Composable
    fun Render()

    @Serializable
    sealed class Child {
        class SplashChild(val component: ISplashComponent) : Child()
        class MainChild(val component: IMainComponent) : Child()

    }
}
