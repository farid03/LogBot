package com.vk.logbot.bot

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class BotApplicationTests {

    @MockBean
    @Suppress("unused")
    lateinit var logBot: LogBot

    @Test
    fun contextLoads() {
        1 + 1 shouldBe 2
    }
}
