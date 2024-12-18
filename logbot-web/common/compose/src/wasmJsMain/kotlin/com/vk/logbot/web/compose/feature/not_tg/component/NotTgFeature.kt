package com.vk.logbot.web.compose.feature.not_tg.component

import com.vk.logbot.web.compose.mvi.component.BaseFeature
import com.vk.logbot.web.feature.not_tg.component.contact.NotTgAction
import com.vk.logbot.web.feature.not_tg.component.contact.NotTgIntent
import com.vk.logbot.web.compose.feature.not_tg.component.contact.NotTgState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NotTgFeature() : BaseFeature<NotTgState, NotTgIntent, NotTgAction, NotTgReducer>(),
    KoinComponent {
    override fun initState(): NotTgState = NotTgState
    override val reducer: NotTgReducer by inject<NotTgReducer>()
    override fun handleIntent(intent: NotTgIntent) {
    }

}