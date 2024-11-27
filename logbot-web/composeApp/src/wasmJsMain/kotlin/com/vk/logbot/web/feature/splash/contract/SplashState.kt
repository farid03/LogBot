package com.vk.logbot.web.feature.splash.contract

import com.vk.logbot.web.core.mvi.contract.MviState

data class SplashState(val isLoading: Boolean = false) : MviState