package com.vk.logbot.web.feature.config.item.component.contract

import com.vk.logbot.web.core.mvi.contract.MviAction

sealed class ConfigAction : MviAction {
    data class OnError(val message: String?) : ConfigAction()
    data class OnIsCreate(val isCreate: Boolean) : ConfigAction()
    data class UpdateName(val name: String) : ConfigAction()
    data class UpdateMessage(val message: String) : ConfigAction()
    data class UpdateRegular(val regular: String) : ConfigAction()
}