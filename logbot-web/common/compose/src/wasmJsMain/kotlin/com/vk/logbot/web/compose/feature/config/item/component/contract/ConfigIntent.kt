package com.vk.logbot.web.compose.feature.config.item.component.contract

import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.data.model.UserInfo
import com.vk.logbot.web.core.mvi.contract.MviIntent

sealed class ConfigIntent : MviIntent {
    data class UpdateName(val name: String) : ConfigIntent()
    data class UpdateMessage(val message: String) : ConfigIntent()
    data class UpdateRegular(val regular: String) : ConfigIntent()
    data class InitData(val userInfo: UserInfo,val config: ConfigFile?) : ConfigIntent()

    data object Save : ConfigIntent()
    data object Delete : ConfigIntent()
}