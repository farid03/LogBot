package com.vk.logbot.web.feature.main.contract

import com.vk.logbot.web.core.mvi.contract.MviState

data class MainState(val isLoading: Boolean = false) : MviState