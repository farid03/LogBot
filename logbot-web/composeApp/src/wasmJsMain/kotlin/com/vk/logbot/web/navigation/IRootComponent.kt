package com.vk.logbot.web.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.vk.logbot.web.core.mvi.component.BaseComponent
import com.vk.logbot.web.feature.config.item.component.IConfigComponent
import com.vk.logbot.web.feature.config.list.component.IListConfigComponent
import com.vk.logbot.web.feature.main.component.IMainComponent
import com.vk.logbot.web.feature.not_tg.component.INotTgComponent
import com.vk.logbot.web.feature.splash.component.ISplashComponent
import kotlinx.serialization.Serializable

interface IRootComponent {
    val childStack: Value<ChildStack<*, Child>>
    fun onBackClicked()

    @Composable
    fun Render()

    @Serializable
    sealed class Child {
        data class SplashChild(override val component: ISplashComponent) : Child()
        data class MainChild(override val component: IMainComponent) : Child()
        data class ConfigFilesChild(override val component: IConfigComponent) : Child()
        data class ListConfigFilesChild(override val component: IListConfigComponent) : Child()
        data class NotTgChild(override val component: INotTgComponent) : Child()
        abstract val component: BaseComponent<*>
    }
}
