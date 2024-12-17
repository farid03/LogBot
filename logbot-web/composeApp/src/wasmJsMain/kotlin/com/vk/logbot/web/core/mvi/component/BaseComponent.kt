package com.vk.logbot.web.core.mvi.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonSkippableComposable
import com.arkivanov.decompose.value.Value
import com.vk.logbot.web.core.mvi.contract.MviState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


interface BaseComponent<T : MviState> {
    val state: Value<T>
    val coroutineScope: CoroutineScope
        get() = CoroutineScope(Dispatchers.Default)


    @Composable
    @NonSkippableComposable
    fun Render()
}

