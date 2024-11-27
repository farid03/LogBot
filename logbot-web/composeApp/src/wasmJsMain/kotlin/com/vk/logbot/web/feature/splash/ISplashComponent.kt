package com.vk.logbot.web.feature.splash

import com.vk.logbot.web.core.mvi.component.BaseComponent
import com.vk.logbot.web.feature.splash.contract.SplashState

interface ISplashComponent : BaseComponent<SplashState> {
    fun navigateToMain()
}