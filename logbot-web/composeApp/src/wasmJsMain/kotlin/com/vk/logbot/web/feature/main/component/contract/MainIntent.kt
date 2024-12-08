package com.vk.logbot.web.feature.main.component.contract

import com.vk.logbot.web.core.mvi.contract.MviIntent
import com.vk.logbot.web.model.UserInfo

sealed class MainIntent: MviIntent {
    data class SetUser(val user:UserInfo?) : MainIntent()
}