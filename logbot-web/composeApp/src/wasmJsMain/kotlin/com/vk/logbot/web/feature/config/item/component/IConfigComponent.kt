package com.vk.logbot.web.feature.config.item.component

import com.vk.logbot.web.core.mvi.component.BaseComponent
import com.vk.logbot.web.feature.config.item.component.contract.ConfigState

interface IConfigComponent : BaseComponent<ConfigState> {
    fun updateName(name: String)
    fun updateRegular(regular: String)
    fun updateMessage(message: String)
    fun deleteConfig()
    fun save()

}

interface ConfigDelegate {
    fun updateName(name: String)
    fun updateRegular(regular: String)
    fun updateMessage(message: String)

    fun deleteConfig()
    fun save()

}

fun IConfigComponent.toDelegate() = object : ConfigDelegate {
    override fun updateName(name: String) {
        return this@toDelegate.updateName(name)
    }


    override fun updateRegular(regular: String) {
        return this@toDelegate.updateRegular(regular)
    }

    override fun updateMessage(message: String) {
        return this@toDelegate.updateMessage(message)
    }

    override fun deleteConfig() {
        return this@toDelegate.deleteConfig()
    }

    override fun save() {
        return this@toDelegate.save()
    }
}