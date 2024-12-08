package com.vk.logbot.web.feature.splash.component

import com.vk.logbot.web.core.mvi.component.BaseComponent
import com.vk.logbot.web.feature.splash.component.contract.SplashState

interface ISplashComponent : BaseComponent<SplashState> {
    fun navigateToMain()
}