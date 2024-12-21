package com.vk.logbot.webjmix

import com.vaadin.flow.component.page.AppShellConfigurator
import com.vaadin.flow.component.page.Push
import com.vaadin.flow.server.PWA
import com.vaadin.flow.theme.Theme
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.core.env.Environment

@Push
@Theme(value = "logbot-web-jmix")
@PWA(name = "Logbot Web Jmix", shortName = "Logbot Web Jmix")
@SpringBootApplication
class WebJmixApplication(
    private val environment: Environment
) : AppShellConfigurator {

    private val logger = KotlinLogging.logger {}

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(WebJmixApplication::class.java, *args)
        }
    }

    @EventListener
    fun printApplicationUrl(event: ApplicationStartedEvent?) {
        val logMessage = "Application started at http://localhost:" +
                (environment.getProperty("local.server.port") ?: "") +
                (environment.getProperty("server.servlet.context-path") ?: "")
        logger.info { logMessage }
    }
}
