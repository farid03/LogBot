package com.vk.logbot.web.telegram

@JsName("Telegram")
private external val telegram: Telegram

private external interface Telegram {
    @JsName("webApp")
    val webApp: WebApp
}

external class WebApp {
    //https://core.telegram.org/bots/webapps#initializing-mini-apps
    @JsName("initDataUnsafe")
    val initDataUnsafe: WebAppInitData
}

val webApp = telegram.webApp