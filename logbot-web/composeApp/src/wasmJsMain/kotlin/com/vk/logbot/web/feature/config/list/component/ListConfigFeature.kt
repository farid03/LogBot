package com.vk.logbot.web.feature.config.list.component

import com.vk.logbot.web.core.mvi.component.BaseFeature
import com.vk.logbot.web.feature.config.list.component.contract.ListConfigAction
import com.vk.logbot.web.feature.config.list.component.contract.ListConfigIntent
import com.vk.logbot.web.feature.config.list.component.contract.ListConfigNavigation
import com.vk.logbot.web.feature.config.list.component.contract.ListConfigState
import com.vk.logbot.web.model.ConfigFile


class ListConfigFeature() : BaseFeature<ListConfigState, ListConfigIntent, ListConfigAction, ListConfigReducer>() {
    override val reducer: ListConfigReducer = ListConfigReducer()
    override fun initState(): ListConfigState = ListConfigState()

    init {
        getConfigsFiles()
    }

    private fun getConfigsFiles() {
        onAction(
            ListConfigAction.SetConfigs(
                listOf(
                    ConfigFile("1", "Error in vk", "Connection lose", "regular"),
                    ConfigFile("2", "Error in ok", "Connection lose", "regular"),
                    ConfigFile("3", "Error in tg", "Connection lose", "regular"),
                    ConfigFile("4", "Error in WUP", "Connection lose", "regular"),
                    ConfigFile("5", "Error in HH", "Connection lose", "regular"),
                    ConfigFile("6", "Error in FF", "Connection lose", "regular"),
                )
            )
        )
    }

    override fun handleIntent(intent: ListConfigIntent) {
        when (intent) {
            is ListConfigIntent.ShowConfigFile -> showConfig(intent.config)
        }
    }

    private fun showConfig(config: ConfigFile) {
        onNavigation(ListConfigNavigation.Config(config))
    }

}