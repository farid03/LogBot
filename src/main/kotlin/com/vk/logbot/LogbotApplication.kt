package com.vk.logbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LogbotApplication

fun main(args: Array<String>) {
	runApplication<LogbotApplication>(*args)
}
