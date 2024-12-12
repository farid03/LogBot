package com.vk.logbot.web.feature.main.component.contract

import com.vk.logbot.web.core.mvi.contract.Navigation

sealed class MainNavigation: Navigation {
    data object ShowConfigFiles: MainNavigation()
    data object ShowCreateConfigFile: MainNavigation()

}