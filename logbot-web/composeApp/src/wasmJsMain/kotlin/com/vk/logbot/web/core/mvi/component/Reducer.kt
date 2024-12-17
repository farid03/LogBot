package com.vk.logbot.web.core.mvi.component

import com.vk.logbot.web.core.mvi.contract.MviAction
import com.vk.logbot.web.core.mvi.contract.MviState

interface Reducer<A : MviAction, S : MviState> {
    fun reduce(action: A, state: S): S
}

