package com.vk.logbot.web.feature.config.list.component.contract

import com.vk.logbot.web.core.mvi.contract.MviIntent
import com.vk.logbot.web.model.ConfigFile

sealed class ListConfigIntent : MviIntent {
    data class ShowConfigFile(val config: ConfigFile) : ListConfigIntent()
}