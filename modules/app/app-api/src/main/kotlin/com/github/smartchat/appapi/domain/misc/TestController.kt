package com.github.smartchat.appapi.domain.misc

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {

    @RequestMapping("/hello")
    fun hello(): String {
        return "Hello World"
    }
}
