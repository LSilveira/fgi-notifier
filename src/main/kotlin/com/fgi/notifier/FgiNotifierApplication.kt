package com.fgi.notifier

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class FgiNotifierApplication

fun main(args: Array<String>) {
	runApplication<FgiNotifierApplication>(*args)
}
