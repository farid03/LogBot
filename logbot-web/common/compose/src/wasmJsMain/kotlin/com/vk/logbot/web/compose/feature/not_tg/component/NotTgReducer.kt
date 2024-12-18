package com.vk.logbot.web.compose.feature.not_tg.component

import com.vk.logbot.web.compose.mvi.component.Reducer
import com.vk.logbot.web.feature.not_tg.component.contact.NotTgAction
import com.vk.logbot.web.compose.feature.not_tg.component.contact.NotTgState

class NotTgReducer : Reducer<NotTgAction, NotTgState> {

    override fun reduce(action: NotTgAction, state: NotTgState): NotTgState {
        return when (action) {
            else -> state
        }
    }
}