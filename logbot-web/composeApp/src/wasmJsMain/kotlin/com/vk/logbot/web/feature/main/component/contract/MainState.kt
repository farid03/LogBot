package com.vk.logbot.web.feature.main.component.contract

import com.vk.logbot.web.core.mvi.contract.MviState
import com.vk.logbot.web.model.UserInfo

data class MainState(val user: UserInfo? = null) : MviState