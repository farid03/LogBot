package com.vk.logbot.web.compose.feature.config.item.component.contract

import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.data.model.UserInfo
import com.vk.logbot.web.core.mvi.contract.MviAction

sealed class ConfigAction : MviAction {
    data class OnError(val message: String?) : ConfigAction()
    data class InitData(val userInfo: UserInfo, val configFile: ConfigFile?) : ConfigAction()
    data class UpdateName(val name: String) : ConfigAction()
    data class UpdateMessage(val message: String) : ConfigAction()
    data class UpdateRegular(val regular: String) : ConfigAction()
}