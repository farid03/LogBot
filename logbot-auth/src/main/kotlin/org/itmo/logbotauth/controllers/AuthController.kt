package org.itmo.logbotauth.controllers

import com.vk.logbot.commons.dto.auth.AuthDto
import com.vk.logbot.commons.dto.auth.AuthStateDto
import org.itmo.logbotauth.services.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping
    fun authTelegramUser(@RequestBody authDto: AuthDto) = AuthStateDto(
        authenticated = authService.authTelegramUser(authDto.telegramId, authDto.authCode)
    )
}