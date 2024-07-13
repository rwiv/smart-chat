package com.github.smartchat.domaincore

import org.springframework.stereotype.Service

@Service
class TestService {

    fun getTestData(): String {
        return "hello"
    }
}