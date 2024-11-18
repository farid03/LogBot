package com.vk.logbot.auth.services

import com.vk.logbot.auth.models.UserCredentials
import org.springframework.stereotype.Service

@Service
class AuthService {

    fun authUserByUserCredentials(userCredentials: UserCredentials): Boolean {
        return userCredentials.idCompany == "111";
    }
}