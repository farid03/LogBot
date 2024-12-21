package com.vk.logbot.webjmix.util

import io.jmix.flowui.component.validation.ValidationErrors
import org.springframework.stereotype.Component
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

@Component
class RegExpValidator {

    fun validateRegExp(regExp: String): ValidationErrors {
        try {
            Pattern.compile(regExp)
            return ValidationErrors.none()
        } catch (e: PatternSyntaxException) {
            return ValidationErrors.of("Регулярное выражение некорректно")
        }
    }
}