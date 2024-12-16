package com.vk.logbot.server.services

import com.vk.logbot.server.models.Config
import org.springframework.stereotype.Service

@Service
class LogFilterService(
    private val configService: ConfigService
) {
    fun retrieveConfigsForLogs(logs: Collection<String>): Map<String,List<Config>> =
        logs.associate {
            it to retrieveConfigsForLog(configService.getActiveConfigs(), it)
        }

    private fun String.match(regex: String) =
        regex.toRegex().matches(this)

    private fun retrieveConfigsForLog(configs: List<Config>, log: String): List<Config> =
        configs.filter {
            log.match(it.regExp)
        }
}