package com.vk.logbot.server.services

import com.vk.logbot.server.models.Config
import com.vk.logbot.server.models.Log
import org.springframework.stereotype.Service
import java.util.Date

@Service
class LogService {

    fun getLogsByDateAndConfig(from: Date, to: Date, config: Config?): List<Log> {
        return listOf(Log(1,  "12:43:47,892 ERROR Application:9 - Hello World!"), Log(2, "13:53:47,892 DEBUG0 Application:10 - Hello World!"))
    }
}