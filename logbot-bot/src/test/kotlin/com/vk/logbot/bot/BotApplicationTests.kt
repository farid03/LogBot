package com.vk.logbot.bot

import com.vk.logbot.bot.config.ExternalServicesConfig
import com.vk.logbot.bot.config.KafkaConfig
import com.vk.logbot.bot.service.kafka.BotMessagesListener
import com.vk.logbot.serverrestclient.AuthClient
import com.vk.logbot.serverrestclient.ServerClient
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@Suppress("UNUSED")
class BotApplicationTests {

    @MockBean
    lateinit var logBot: LogBot

    @MockBean
    lateinit var externalServicesConfig: ExternalServicesConfig

    @MockBean
    lateinit var authClient: AuthClient

    @MockBean
    lateinit var serverClient: ServerClient

    @MockBean
    lateinit var kafkaConfig: KafkaConfig

    @MockBean
    lateinit var botMessagesListener: BotMessagesListener

    @Test
    fun contextLoads() {
        1 + 1 shouldBe 2
    }
}
