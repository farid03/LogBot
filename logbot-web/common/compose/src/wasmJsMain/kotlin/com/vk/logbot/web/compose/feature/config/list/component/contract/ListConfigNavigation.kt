package com.vk.logbot.web.compose.feature.config.list.component.contract

import com.vk.logbot.client.data.model.ConfigFile
  
import com.vk.logbot.web.core.mvi.contract.Navigation

sealed class ListConfigNavigation : Navigation {
    data class Config(val config: ConfigFile) : ListConfigNavigation()
    data object Back : ListConfigNavigation()
}