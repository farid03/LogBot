package com.vk.logbot.web.compose.feature.config.list.component

import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.web.compose.mvi.component.BaseComponent
import com.vk.logbot.web.compose.feature.config.list.component.contract.ListConfigState

interface IListConfigComponent : BaseComponent<ListConfigState> {
    fun navigateToConfig(config: ConfigFile)
}

interface ListConfigDelegate {
    fun navigateToConfig(config:ConfigFile)

}

fun IListConfigComponent.toDelegate() = object : ListConfigDelegate {
    override fun navigateToConfig(config:ConfigFile) {
        this@toDelegate.navigateToConfig(config)
    }

}