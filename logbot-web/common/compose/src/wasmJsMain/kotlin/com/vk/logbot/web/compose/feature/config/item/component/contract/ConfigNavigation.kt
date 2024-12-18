package com.vk.logbot.web.feature.config.item.component.contract

import com.vk.logbot.web.core.mvi.contract.Navigation

sealed class ConfigNavigation : Navigation {
    data object Main : ConfigNavigation()
    data object Back : ConfigNavigation()
}