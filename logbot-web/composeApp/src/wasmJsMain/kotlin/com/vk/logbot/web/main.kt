package com.vk.logbot.web

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import androidx.compose.ui.window.ComposeViewport
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.vk.logbot.web.di.mainModule
import com.vk.logbot.web.navigation.RootComponent
import com.vk.logbot.web.telegram.webApp
import kotlinx.browser.document
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.startKoin


@OptIn(ExperimentalComposeUiApi::class, KoinInternalApi::class)
fun main() {
    println("debug start koin render")

    val t = startKoin {
        modules(
            mainModule
        )
    }
    println("debug end init ")

    println("debug lifecycle")
    val lifecycle = LifecycleRegistry()
    lifecycle.resume()
    println("debug end init ")
    println("debug root")
    val root = RootComponent(DefaultComponentContext(lifecycle), null)
//    CanvasBasedWindow(canvasElementId = "ComposeTarget") { MaterialTheme { root.Render() } }
    ComposeViewport(document.body!!) {
        MaterialTheme { root.Render() }
    }

    webApp.initDataUnsafe.user?.id //так можно получить userId
}