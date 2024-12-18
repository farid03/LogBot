package com.vk.logbot.web.navigation.contract

import com.vk.logbot.web.core.mvi.contract.MviState

data class RootState(val isLoading: Boolean) : MviState