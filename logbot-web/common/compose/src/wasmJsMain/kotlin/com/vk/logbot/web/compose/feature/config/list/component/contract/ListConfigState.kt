package com.vk.logbot.web.compose.feature.config.list.component.contract

import com.vk.logbot.client.data.model.ConfigFile

import com.vk.logbot.web.core.mvi.contract.MviState

data class ListConfigState(
    val configs: List<ConfigFile> = emptyList()
) : MviState {
}