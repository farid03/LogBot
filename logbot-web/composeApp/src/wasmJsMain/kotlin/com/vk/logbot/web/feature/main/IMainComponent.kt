package com.vk.logbot.web.feature.main

import com.vk.logbot.web.core.mvi.component.BaseComponent
import com.vk.logbot.web.feature.main.contract.MainState

interface IMainComponent : BaseComponent<MainState> {
    fun navigateToConfigFiles()
}