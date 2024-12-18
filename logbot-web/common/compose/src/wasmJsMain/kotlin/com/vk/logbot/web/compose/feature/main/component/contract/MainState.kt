package com.vk.logbot.web.compose.feature.main.component.contract

import com.vk.logbot.client.data.model.UserInfo
import com.vk.logbot.web.core.mvi.contract.MviState

data class MainState(val user: UserInfo? = null) : MviState