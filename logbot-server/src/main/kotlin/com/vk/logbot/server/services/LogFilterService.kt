package com.vk.logbot.server.services

import com.vk.logbot.server.models.Config
import org.springframework.stereotype.Service

@Service
class LogFilterService(
    private val configService: ConfigService
) {
    fun retrieveConfigsForLogs(logs: Collection<String>): Map<String,List<Config>> =
        logs.associate {
            it to retrieveConfigsForLog(it)
        }

    fun retrieveConfigsForLog(log: String): List<Config> =
        configService.getActiveConfigs().filter {
            log.match(it.regExp)
        }

    private fun String.match(regex: String) =
        regex.toRegex().matches(this)
}