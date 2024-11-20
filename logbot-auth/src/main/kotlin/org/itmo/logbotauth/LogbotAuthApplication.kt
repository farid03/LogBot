package org.itmo.logbotauth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LogbotAuthApplication

fun main(args: Array<String>) {
	runApplication<LogbotAuthApplication>(*args)
}
