package com.vk.logbot.server.jobs

import com.vk.logbot.commons.models.BotMessage
import com.vk.logbot.server.models.Config
import com.vk.logbot.server.repositories.LogEntryRepository
import com.vk.logbot.server.services.LogEntryKafkaService
import com.vk.logbot.server.services.LogFilterService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit

@Component
class ProcessLogsJob(
    private val logEntryRepository: LogEntryRepository,
    private val logFilterService: LogFilterService,
    private val kafkaService: LogEntryKafkaService
) {

    @Transactional
    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    fun processLogs() {
        val maxReceivedAt = logEntryRepository.getMaxReceivedAt() ?: ZonedDateTime.now()
        println("process logs before $maxReceivedAt")
        logEntryRepository.findByReceivedAtLessThanEqual(maxReceivedAt)
            .map { it.message }
            .let { logFilterService.retrieveConfigsForLogs(it) }
            .filter { it.value.isNotEmpty() }
            .toBotMessages()
            .forEach { message -> kafkaService.sendMessage(message) }
        logEntryRepository.deleteByReceivedAtLessThanEqual(maxReceivedAt)
    }

    private fun Map<String, List<Config>>.toBotMessages(): List<BotMessage> =
        this.flatMap { (key, value) ->
            value.map {
                BotMessage(
                    message = it.message,
                    userIds = listOf(it.userId)
                )
            }
        }
}