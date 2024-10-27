package com.vk.logbot.bot

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BotApplicationTests {

    @Test
    fun contextLoads() {
        1 + 1 shouldBe 2
    }

}
