package com.vk.logbot.web.compose.feature.config.item.component.contract

import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.web.core.mvi.contract.MviState

data class ConfigState(
    val isCreating: Boolean = false,
    val errorMessage: String? = null,
    val configFile: ConfigFile = ConfigFile.EMPTY,
) : MviState