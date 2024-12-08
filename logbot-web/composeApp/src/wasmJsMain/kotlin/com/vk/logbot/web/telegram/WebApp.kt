package com.vk.logbot.web.telegram

val webApp: WebApp by lazy { WebApp(js("window.Telegram.WebApp")) }

class WebApp internal constructor(
    private val jsDelegate: WebAppJs
) {
    val initDataUnsafe: WebAppInitData get() = jsDelegate.initDataUnsafe
}

internal external class WebAppJs {

    //https://core.telegram.org/bots/webapps#initializing-mini-apps
    @JsName("initDataUnsafe")
    val initDataUnsafe: WebAppInitData
}