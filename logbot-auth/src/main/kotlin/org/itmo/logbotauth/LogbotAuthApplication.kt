package org.itmo.logbotauth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class LogbotAuthApplication

fun main(args: Array<String>) {
    runApplication<LogbotAuthApplication>(*args)
}
