package com.vk.logbot.web


import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.create
import com.arkivanov.essenty.lifecycle.resume
import com.vk.logbot.client.core.telegram.toUser
import com.vk.logbot.client.core.telegram.webApp
import com.vk.logbot.client.data.model.UserInfo
import com.vk.logbot.web.compose.di.mainModule
import com.vk.logbot.web.compose.navigation.RootComponent
import kotlinx.browser.document
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
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
    lifecycle.create()
    lifecycle.resume()
    println("debug end init ")
    println("debug root")
    val user = run {
        try {
            webApp.initDataUnsafe.user?.toUser()
        } catch (e: Exception) {
            null
        }
    } ?: if (BuildKonfig.SUPPORT_BROWSER_WITHOUT_TELEGRAM) {
        UserInfo("Develop", "Develop")
    } else {
        null
    }
    val root = RootComponent(
        componentContext = DefaultComponentContext(lifecycle),
        userInfo = user,
        navigationOptions = null
    )
    ComposeViewport(document.body!!) {
        MaterialTheme { root.Render() }
    }
}
