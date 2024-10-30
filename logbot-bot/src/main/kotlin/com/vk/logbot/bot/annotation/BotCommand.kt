package com.vk.logbot.bot.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Component
annotation class BotCommand(
    @get:AliasFor(annotation = Component::class) val value: String = ""
)
