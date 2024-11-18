package com.vk.logbot.auth.controllers

import com.vk.logbot.auth.models.UserCredentials
import com.vk.logbot.auth.services.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController (private val authService: AuthService) {

    @PostMapping
    fun authUser(@RequestParam("user") user: UserCredentials): Boolean {
        return authService.authUserByUserCredentials(user);
    }
}