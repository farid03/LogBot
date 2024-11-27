package com.vk.logbot.web.feature.main.contract

import com.vk.logbot.web.core.mvi.contract.Navigation

sealed class MainNavigation: Navigation {
    data object ShowMain: MainNavigation()
}