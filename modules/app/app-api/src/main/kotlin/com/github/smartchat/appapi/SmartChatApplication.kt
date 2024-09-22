package com.github.smartchat.appapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.github.smartchat")
class SmartChatApplication

fun main(args: Array<String>) {
    runApplication<SmartChatApplication>(*args)
}