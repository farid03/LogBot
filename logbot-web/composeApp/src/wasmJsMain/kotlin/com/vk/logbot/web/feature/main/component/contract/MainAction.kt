package com.vk.logbot.web.feature.main.component.contract

import com.vk.logbot.web.core.mvi.contract.MviAction
import com.vk.logbot.web.model.UserInfo

sealed class MainAction: MviAction {
    data class SetUser(val user: UserInfo) : MainAction()
}