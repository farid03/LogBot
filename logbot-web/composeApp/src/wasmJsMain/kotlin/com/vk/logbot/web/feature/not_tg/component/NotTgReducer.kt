package com.vk.logbot.web.feature.not_tg.component

import com.vk.logbot.web.core.mvi.component.BaseComponent
import com.vk.logbot.web.core.mvi.component.Reducer
import com.vk.logbot.web.feature.not_tg.component.contact.NotTgAction
import com.vk.logbot.web.feature.not_tg.component.contact.NotTgState
import com.vk.logbot.web.navigation.RootComponent

class NotTgReducer : Reducer<NotTgAction, NotTgState> {

    override fun reduce(action: NotTgAction, state: NotTgState): NotTgState {
        return when (action) {
            else -> state
        }
    }
}