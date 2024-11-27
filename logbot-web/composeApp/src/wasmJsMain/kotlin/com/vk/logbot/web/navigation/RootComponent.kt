package com.vk.logbot.web.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.vk.logbot.web.feature.main.MainComponent
import com.vk.logbot.web.feature.splash.SplashComponent
import com.vk.logbot.web.model.UserInfo
import com.vk.logbot.web.navigation.IRootComponent.Child.*
import kotlinx.serialization.Serializable


class RootComponent(
    componentContext: ComponentContext,
    private val navigationOptions: ScreenConfig?,
) : IRootComponent, ComponentContext by componentContext {


    val feature: RootFeature = RootFeature()

    private val navigation = StackNavigation<ScreenConfig>()

    override val childStack: Value<ChildStack<*, IRootComponent.Child>> = childStack(
        source = navigation,
        serializer = ScreenConfig.serializer(),
        handleBackButton = true,
        initialStack = { parseInitialStack(navigationOptions) },
        childFactory = ::child,
    )


    override fun onBackClicked() {
        navigation.pop()
    }

    @Composable
    override fun Render() {
        println("debug root render")
        CompositionLocalProvider {
            Children(
                stack = this.childStack,
                modifier = Modifier.fillMaxSize(),
                animation = stackAnimation(fade())
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    when (val child = it.instance) {
                        is SplashChild ->
                            child.component.Render()

                        is MainChild -> {
                            child.component.Render()
                        }
                    }
                }
            }
        }
    }


    private fun parseInitialStack(navigationOptions: ScreenConfig?): List<ScreenConfig> {
        return when (navigationOptions) {
            ScreenConfig.Splash -> listOf(ScreenConfig.Splash)
            else -> listOf(ScreenConfig.Splash)
        }
    }


    private fun logout() {
        navigation.replaceAll(ScreenConfig.Splash)
    }

    private fun child(
        config: ScreenConfig,
        componentContext: ComponentContext,
    ): IRootComponent.Child {
        return when (config) {
            ScreenConfig.Splash -> {
                SplashChild(
                    SplashComponent(
                        componentContext = componentContext,
                        navigateMain = {
                            navigation.replaceCurrent(
                                ScreenConfig.Main(UserInfo(1L, "1"))
                            )
                        })
                )
            }

            is ScreenConfig.Main -> {
                MainChild(
                    MainComponent(componentContext, {})
                )
            }
        }


    }

    @Serializable
    sealed class ScreenConfig {
        @Serializable
        data object Splash : ScreenConfig()

        @Serializable
        data class Main(val userInfo: UserInfo) : ScreenConfig()

    }
}


