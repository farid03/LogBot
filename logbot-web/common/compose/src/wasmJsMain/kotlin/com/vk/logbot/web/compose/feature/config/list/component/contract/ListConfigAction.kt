package com.vk.logbot.web.compose.feature.config.list.component.contract

import com.vk.logbot.client.data.model.ConfigFile
  
import com.vk.logbot.web.core.mvi.contract.MviAction

sealed class ListConfigAction : MviAction {

    data class SetConfigs(val configs: List<ConfigFile>) : ListConfigAction()
}