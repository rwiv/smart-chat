package com.github.smartchat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class SmartChatApplication

fun main(args: Array<String>) {
    runApplication<SmartChatApplication>(*args)
}
