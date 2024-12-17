package com.vk.logbot.web.feature.not_tg.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.vk.logbot.web.core.mvi.component.BaseComponent
import com.vk.logbot.web.feature.not_tg.component.contact.NotTgState
import com.vk.logbot.web.navigation.RootComponent

class NotTgComponent(
    componentContext: ComponentContext
) : INotTgComponent, ComponentContext by componentContext {

    private val feature: NotTgFeature = NotTgFeature()
    override val state: Value<NotTgState> = feature.state

    @Composable
    override fun Render() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Вы зашли не через телеграм")
        }
    }
}