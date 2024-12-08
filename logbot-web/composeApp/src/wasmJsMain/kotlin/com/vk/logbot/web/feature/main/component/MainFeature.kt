package com.vk.logbot.web.feature.main.component

import com.vk.logbot.web.core.mvi.component.BaseFeature
import com.vk.logbot.web.feature.main.component.contract.MainAction
import com.vk.logbot.web.feature.main.component.contract.MainIntent
import com.vk.logbot.web.feature.main.component.contract.MainState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainFeature() : BaseFeature<MainState, MainIntent, MainAction, MainReducer>(),
    KoinComponent {
    override fun initState(): MainState = MainState()
    override val reducer: MainReducer by inject<MainReducer>()

    override fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.SetUser -> onAction(MainAction.SetUser(intent.user))
        }
    }
}