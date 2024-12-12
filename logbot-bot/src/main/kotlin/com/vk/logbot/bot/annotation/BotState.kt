package com.vk.logbot.bot.annotation

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component

/**
 * Аннотация для классов состояний бота. Объединяет в себе [Component] и [Qualifier].
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Component
@Qualifier
annotation class BotState(
    @get:AliasFor(annotation = Qualifier::class) val value: String = ""
)