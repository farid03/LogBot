package com.vk.logbot.web.feature.not_tg.component

import com.vk.logbot.web.core.mvi.component.BaseComponent
import com.vk.logbot.web.core.mvi.component.BaseFeature
import com.vk.logbot.web.feature.not_tg.component.contact.NotTgAction
import com.vk.logbot.web.feature.not_tg.component.contact.NotTgIntent
import com.vk.logbot.web.feature.not_tg.component.contact.NotTgState
import com.vk.logbot.web.navigation.RootComponent

class NotTgFeature() : BaseFeature<NotTgState, NotTgIntent, NotTgAction, NotTgReducer>() {
    override fun initState(): NotTgState = NotTgState

    override val reducer: NotTgReducer = NotTgReducer()
    override fun handleIntent(intent: NotTgIntent) {

    }

}