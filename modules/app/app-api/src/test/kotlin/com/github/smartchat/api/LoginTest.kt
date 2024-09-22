package com.github.smartchat.api

import com.github.smartchat.domaincore.domain.account.AccountPublic
import com.github.smartchat.helpers.TestHelper
import com.github.smartchat.security.filters.LoginRequest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity

class LoginTest {

    private val restTemplate = TestRestTemplate()

    @Test
    fun testLogin() {
        val loginForm = LoginRequest("user1@gmail.com", "1234")

        val postRequest = RequestEntity
            .post("http://localhost:11730/user/login")
            .contentType(MediaType.APPLICATION_JSON)
            .body(loginForm)

        val postResponseEntity = restTemplate.exchange(postRequest, AccountPublic::class.java)
        println(postResponseEntity.body)

        val jSessionId = TestHelper().getJSessionId(postResponseEntity.headers)

        val getRequest = RequestEntity
            .get("http://localhost:11730/test/hello")
            .header("Cookie", "JSESSIONID=$jSessionId")
            .build()

        val getResponseEntity = restTemplate.exchange(getRequest, String::class.java)
        println(getResponseEntity.body)
    }
}