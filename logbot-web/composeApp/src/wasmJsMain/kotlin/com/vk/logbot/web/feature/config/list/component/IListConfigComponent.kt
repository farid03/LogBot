package com.vk.logbot.web.feature.config.list.component

import com.vk.logbot.web.core.mvi.component.BaseComponent
import com.vk.logbot.web.feature.config.list.component.contract.ListConfigState
import com.vk.logbot.web.model.ConfigFile

interface IListConfigComponent : BaseComponent<ListConfigState> {
    fun navigateToConfig(config:ConfigFile)
}

interface ListConfigDelegate {
    fun navigateToConfig(config:ConfigFile)

}

fun IListConfigComponent.toDelegate() = object : ListConfigDelegate {
    override fun navigateToConfig(config:ConfigFile) {
        this@toDelegate.navigateToConfig(config)
    }

}