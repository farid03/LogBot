package com.vk.logbot.server.controllers

import org.springframework.stereotype.Controller
import org.springframework.messaging.handler.annotation.MessageMapping


@Controller
class WebSocketController {

    @MessageMapping("/send-message")
    fun handleMessage(message: String) {
        println("Received message via WebSocket: $message")
    }
}