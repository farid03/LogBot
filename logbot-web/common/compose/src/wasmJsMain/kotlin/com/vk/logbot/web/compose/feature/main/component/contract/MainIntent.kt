package com.vk.logbot.web.compose.feature.main.component.contract

import com.vk.logbot.client.data.model.UserInfo

  import com.vk.logbot.web.core.mvi.contract.MviIntent

sealed class MainIntent: MviIntent {
    data class SetUser(val user: UserInfo?) : MainIntent()
}