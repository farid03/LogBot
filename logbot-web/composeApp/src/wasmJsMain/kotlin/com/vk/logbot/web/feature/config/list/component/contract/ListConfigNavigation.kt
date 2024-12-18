package com.vk.logbot.web.feature.config.list.component.contract

import com.vk.logbot.web.core.mvi.contract.Navigation
import com.vk.logbot.web.model.ConfigFile

sealed class ListConfigNavigation : Navigation {
    data class Config(val config: ConfigFile) : ListConfigNavigation()
    data object Back : ListConfigNavigation()
}