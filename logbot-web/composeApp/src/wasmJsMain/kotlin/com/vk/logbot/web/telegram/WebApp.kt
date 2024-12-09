package com.vk.logbot.web.telegram

@JsName("window")
external val window: Window

external class Window {
    @JsName("Telegram")
    val telegram: Telegram
}

external class Telegram {
    @JsName("WebApp")
    val webApp: WebApp
}

external class WebApp {
    //https://core.telegram.org/bots/webapps#initializing-mini-apps
    @JsName("initDataUnsafe")
    val initDataUnsafe: WebAppInitData
}

val webApp = window.telegram.webApp