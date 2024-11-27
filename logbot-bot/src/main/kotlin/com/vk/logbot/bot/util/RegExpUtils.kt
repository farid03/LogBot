package com.vk.logbot.bot.util

import org.springframework.stereotype.Component
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

@Component
class RegExpUtils {

    fun validateRegExp(regExp: String): Boolean {
        try {
            Pattern.compile(regExp)
            return true
        } catch (e: PatternSyntaxException) {
            return false
        }
    }
}