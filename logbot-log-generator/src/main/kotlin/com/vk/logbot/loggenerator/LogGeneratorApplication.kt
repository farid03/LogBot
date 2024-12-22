package com.vk.logbot.loggenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class LogGeneratorApplication

fun main(args: Array<String>) {
	runApplication<LogGeneratorApplication>(*args)
}
