package com.vk.logbot.web.feature.config.list.component.contract

import com.vk.logbot.web.core.mvi.contract.MviAction
import com.vk.logbot.web.model.ConfigFile

sealed class ListConfigAction : MviAction {

    data class SetConfigs(val configs: List<ConfigFile>) : ListConfigAction()
}