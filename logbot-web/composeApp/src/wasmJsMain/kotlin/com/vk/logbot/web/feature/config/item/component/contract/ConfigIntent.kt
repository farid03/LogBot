package com.vk.logbot.web.feature.config.item.component.contract

import com.vk.logbot.web.core.mvi.contract.MviIntent
import com.vk.logbot.web.model.ConfigFile

sealed class ConfigIntent : MviIntent {
    data class UpdateName(val name: String) : ConfigIntent()
    data class UpdateMessage(val message: String) : ConfigIntent()
    data class UpdateRegular(val regular: String) : ConfigIntent()
    data class InitData(val config: ConfigFile?) : ConfigIntent()

    data object Save : ConfigIntent()
    data object Delete : ConfigIntent()
}