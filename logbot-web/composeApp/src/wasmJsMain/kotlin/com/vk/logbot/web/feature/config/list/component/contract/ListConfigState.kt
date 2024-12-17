package com.vk.logbot.web.feature.config.list.component.contract

import com.vk.logbot.web.core.mvi.contract.MviState
import com.vk.logbot.web.model.ConfigFile

data class ListConfigState(
    val configs: List<ConfigFile> = emptyList()
) : MviState {
}