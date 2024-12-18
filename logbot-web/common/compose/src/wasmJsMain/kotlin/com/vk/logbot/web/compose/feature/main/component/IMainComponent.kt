package com.vk.logbot.web.feature.main.component

import com.vk.logbot.web.compose.mvi.component.BaseComponent
import com.vk.logbot.web.compose.feature.main.component.contract.MainState

interface IMainComponent : BaseComponent<MainState> {
    fun navigateToConfigFiles()
    fun navigateToCreateConfigFile()
}


interface MainDelegate {
    fun navigateToConfigFiles()
    fun navigateToCreateConfigFile()

}

fun IMainComponent.toDelegate(): MainDelegate = object : MainDelegate {
    override fun navigateToConfigFiles() {
        this@toDelegate.navigateToConfigFiles()
    }

    override fun navigateToCreateConfigFile() {
        this@toDelegate.navigateToCreateConfigFile()
    }
}