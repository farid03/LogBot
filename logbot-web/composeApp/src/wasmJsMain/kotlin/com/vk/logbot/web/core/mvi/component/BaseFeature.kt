package com.vk.logbot.web.core.mvi.component

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.vk.logbot.web.core.mvi.contract.ActionToChildComponent
import com.vk.logbot.web.core.mvi.contract.MviAction
import com.vk.logbot.web.core.mvi.contract.MviIntent
import com.vk.logbot.web.core.mvi.contract.MviState
import com.vk.logbot.web.core.mvi.contract.Navigation

abstract class BaseFeature<S : MviState, I : MviIntent, A : MviAction, R : Reducer<A, S>> :
    InstanceKeeper.Instance {
    private val lifecycleScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _navigation = Channel<Navigation>()
    val navigation = _navigation.receiveAsFlow()

    private val _actionsToChildComponents = Channel<ActionToChildComponent>()
    val actionsToChildComponents = _actionsToChildComponents.receiveAsFlow()


    var state = MutableValue(this.initState())
        private set


    protected abstract fun initState(): S


    protected abstract val reducer: R

    private fun handleAction(action: A) {
        state.value = reducer.reduce(action, state.value)
    }

    abstract fun handleIntent(intent: I)

    fun onIntent(intent: I) {
        handleIntent(intent)
    }

    fun onAction(action: A) {
        handleAction(action)
    }

    fun onNavigation(route: Navigation) {
        lifecycleScope.launch {
            _navigation.send(route)
        }
    }

    fun onActionToChildComponent(action: ActionToChildComponent) {
        lifecycleScope.launch {
            _actionsToChildComponents.send(action)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.cancel()
    }

}

