package com.vk.logbot.web.feature.splash.component.contract

import com.vk.logbot.web.core.mvi.contract.MviAction

sealed class SplashAction: MviAction {
    data class OnLoad(val isLoad: Boolean) : SplashAction()
}