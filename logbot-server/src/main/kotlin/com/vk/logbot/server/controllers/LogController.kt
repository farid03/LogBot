package com.vk.logbot.server.controllers

import com.vk.logbot.server.models.Config
import com.vk.logbot.server.models.Log
import com.vk.logbot.server.services.ConfigService
import com.vk.logbot.server.services.LogService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("logs")
class LogController(private val logService: LogService) {

    @GetMapping
    fun getLogs(@RequestParam("from") from: Long, @RequestParam("to") to: Long, @RequestParam("config") config: Config?): List<Log> {
        return logService.getLogsByDateAndConfig(from, to, config);
    }
}