package com.vk.logbot.server

import com.vk.logbot.server.models.Config
import com.vk.logbot.server.services.ConfigService
import com.vk.logbot.server.services.LogFilterService
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ServerApplicationTests {

    final val configService: ConfigService = mockk()
    final val logFilterService: LogFilterService = LogFilterService(configService)

    @Test
    fun filterServiceTest() {
        val logError = "2024-12-11 12:00:01 ERROR LogGenerator - Это информационное ERROR уровня"
        val logInfo = "2024-12-11 12:00:01 INFO  LogGenerator - Это сообщение для INFO уровня"
        val logDebug = "2024-12-11 12:00:00 DEBUG LogGenerator - Это сообщение для DEBUG уровня"

        val configInfo = Config(1,1, "configFirst", ".*INFO.*", "Ошибка сервиса", true)
        val configDebug = Config(1,2, "configSecond", ".*DEBUG.*", "Предупреждение", true)

        every { configService.getActiveConfigs() } returns listOf(configInfo, configDebug)

        val result = logFilterService.retrieveConfigsForLogs(listOf(logError, logInfo, logDebug))

        assertThat(result.size).isEqualTo(3)
        assertThat(result[logInfo]?.size ?: 0).isEqualTo(1)
        assertThat(result[logDebug]?.size ?: 0).isEqualTo(1)
        assertTrue(result.containsKey(logError))
        assertTrue(result.containsKey(logInfo))
        assertTrue(result.containsKey(logDebug))
        assertTrue(result[logInfo]?.contains(configInfo) ?: false )
        assertTrue(result[logDebug]?.contains(configDebug) ?: false )
    }
}
