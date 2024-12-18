package com.vk.logbot.web.compose.feature.splash.component

import com.vk.logbot.web.compose.mvi.component.BaseComponent
import com.vk.logbot.web.feature.splash.component.contract.SplashState

interface ISplashComponent : BaseComponent<SplashState> {
    fun navigateToMain()
}