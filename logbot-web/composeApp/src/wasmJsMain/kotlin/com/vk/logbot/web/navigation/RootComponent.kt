package com.vk.logbot.web.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.vk.logbot.web.feature.config.item.component.ConfigComponent
import com.vk.logbot.web.feature.config.list.component.ListConfigComponent
import com.vk.logbot.web.feature.main.component.MainComponent
import com.vk.logbot.web.feature.splash.component.SplashComponent
import com.vk.logbot.web.model.ConfigFile
import com.vk.logbot.web.model.UserInfo
import com.vk.logbot.web.navigation.IRootComponent.Child.*
import kotlinx.serialization.Serializable
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.vk.logbot.web.feature.not_tg.component.NotTgComponent
import com.vk.logbot.web.telegram.WebAppUser
import com.vk.logbot.web.telegram.toUser

class RootComponent(
    componentContext: ComponentContext,
    private val navigationOptions: ScreenConfig?,
    private val userInfo: WebAppUser?,
) : IRootComponent, ComponentContext by componentContext {


    val feature: RootFeature = RootFeature()

    private val navigation = StackNavigation<ScreenConfig>()

    override val childStack: Value<ChildStack<ScreenConfig, IRootComponent.Child>> = childStack(
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
        CompositionLocalProvider {
            Children(
                stack = this.childStack,
                modifier = Modifier.fillMaxSize(),
                animation = stackAnimation(fade())
            ) {

                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        AnimatedVisibility(
                            it.instance.component !is SplashComponent &&
                                    it.instance.component !is NotTgComponent &&
                                    it.instance.component !is MainComponent
                        ) {
                            IconButton(onClick = {
                                navigation.pop()
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "home",
                                    modifier = Modifier.size(40.dp),
                                    tint = Color.Black
                                )
                            }
                        }
                        it.instance.component.Render()
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
                            if (userInfo != null) {
                                navigation.replaceCurrent(
                                    ScreenConfig.Main(userInfo.toUser())
                                )
                            } else {
                                navigation.replaceCurrent(
                                    ScreenConfig.NotTg
                                )
                            }

                        })
                )
            }

            is ScreenConfig.Main -> {
                MainChild(
                    MainComponent(
                        componentContext = componentContext,
                        userInfo = userInfo?.toUser(),
                        navigateConfigFiles = {
                            navigation.bringToFront(
                                ScreenConfig.ListConfigFiles(userInfo = config.userInfo)
                            )
                        },
                        navigateCreateConfigFile = {
                            navigation.bringToFront(
                                ScreenConfig.Config(
                                    userInfo = config.userInfo,
                                    configFile = null
                                )
                            )
                        })
                )
            }

            is ScreenConfig.Config -> {
                ConfigFilesChild(
                    ConfigComponent(
                        componentContext = componentContext,
                        userInfo = config.userInfo,
                        configFile = config.configFile,
                        navigateToMainScreen = {},
                        backToPreviousScreen = { navigation.pop() }
                    )
                )
            }

            is ScreenConfig.ListConfigFiles -> ListConfigFilesChild(
                ListConfigComponent(
                    componentContext = componentContext,
                    userInfo = config.userInfo,
                    navigateBack = { navigation.pop() },
                    navigateToConfigFile = {
                        navigation.bringToFront(
                            ScreenConfig.Config(
                                userInfo = config.userInfo,
                                configFile = it
                            )
                        )
                    }
                )
            )

            ScreenConfig.NotTg -> NotTgChild(
                NotTgComponent(componentContext = componentContext)
            )
        }


    }

    @Serializable
    sealed class ScreenConfig {
        @Serializable
        data object NotTg : ScreenConfig()

        @Serializable
        data object Splash : ScreenConfig()

        @Serializable
        data class Main(val userInfo: UserInfo) : ScreenConfig()


        @Serializable
        data class Config(
            val userInfo: UserInfo,
            val configFile: ConfigFile? = null,
        ) : ScreenConfig()

        @Serializable
        data class ListConfigFiles(val userInfo: UserInfo) : ScreenConfig()

    }
}


