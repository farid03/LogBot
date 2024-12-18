package com.vk.logbot.web.compose.feature.config.list.component.contract

import com.vk.logbot.client.data.model.ConfigFile
  
import com.vk.logbot.web.core.mvi.contract.MviIntent

sealed class ListConfigIntent : MviIntent {
    data class ShowConfigFile(val config: ConfigFile) : ListConfigIntent()
}