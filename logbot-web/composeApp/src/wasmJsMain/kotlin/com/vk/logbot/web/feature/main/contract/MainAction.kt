package com.vk.logbot.web.feature.main.contract

import com.vk.logbot.web.core.mvi.contract.MviAction

sealed class MainAction: MviAction {
    data class OnLoad(val isLoad: Boolean) : MainAction()
}