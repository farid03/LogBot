package com.vk.logbot.web.feature.main.contract

import com.vk.logbot.web.core.mvi.contract.MviIntent

sealed class MainIntent: MviIntent {
    data class OnLoad(val isLoad: Boolean) : MainIntent()
}