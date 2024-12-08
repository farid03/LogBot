package com.vk.logbot.web.feature.config.item.component

import com.vk.logbot.web.core.mvi.component.BaseFeature
import com.vk.logbot.web.feature.config.item.component.contract.ConfigAction
import com.vk.logbot.web.feature.config.item.component.contract.ConfigIntent
import com.vk.logbot.web.feature.config.item.component.contract.ConfigState
import com.vk.logbot.web.model.ConfigFile
import org.koin.core.KoinApplication.Companion.init

class ConfigFeature() :
    BaseFeature<ConfigState, ConfigIntent, ConfigAction, ConfigReducer>() {
    override fun initState(): ConfigState = ConfigState()

    override val reducer = ConfigReducer()
    override fun handleIntent(intent: ConfigIntent) {
        when (intent) {
            is ConfigIntent.UpdateMessage -> updateMessage(intent.message)
            is ConfigIntent.UpdateName -> updateName(intent.name)
            is ConfigIntent.UpdateRegular -> updateRegular(intent.regular)
            is ConfigIntent.InitData -> initData(intent.config)
            ConfigIntent.Delete -> delete()
            ConfigIntent.Save -> save()
        }
    }

    private fun delete() {

    }
    private fun save() {
        ///
    }

    private fun initData(configFile: ConfigFile?) {
        configFile?.let {
            updateName(it.name)
            updateRegular(it.regular)
            updateMessage(it.message)
            onAction(ConfigAction.OnIsCreate(true))
        }
    }

    private fun updateName(name: String) {
        onAction(ConfigAction.UpdateName(name))
    }

    private fun updateMessage(message: String) {
        onAction(ConfigAction.UpdateMessage(message))
    }

    private fun updateRegular(regular: String) {
        onAction(ConfigAction.UpdateRegular(regular))
    }
}