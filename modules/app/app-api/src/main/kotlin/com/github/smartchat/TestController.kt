package com.github.smartchat

import com.github.smartchat.domaincore.TestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val testService: TestService,
) {

    @GetMapping("/hello")
    fun hello(): String {
        return testService.getTestData()
    }
}