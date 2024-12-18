package com.vk.logbot.client.core.telegram

import com.vk.logbot.client.data.model.UserInfo


external class WebAppUser {

    @JsName("id")
    val id: Int
}

fun WebAppUser.toUser() = UserInfo(
    telegramId = this.id.toString(),
    idCompany = ""
)