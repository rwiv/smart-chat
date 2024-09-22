package com.github.smartchat.appapi.api

import com.github.smartchat.domaincore.domain.account.AccountPublic
import com.github.smartchat.appapi.helpers.TestHelper
import com.github.smartchat.appapi.security.filters.LoginRequest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity

class DeniedTest {

    private val restTemplate = TestRestTemplate()

    @Test
    fun ADMIN_권한_요청() {
        val loginForm = LoginRequest("user1@gmail.com", "1234")

        val postRequest = RequestEntity
            .post("http://localhost:8080/user/login")
            .contentType(MediaType.APPLICATION_JSON)
            .body(loginForm)

        val postResponseEntity = restTemplate.exchange(postRequest, AccountPublic::class.java)
        println(postResponseEntity.body)

        val jSessionId = TestHelper().getJSessionId(postResponseEntity.headers)

        val getRequest = RequestEntity
            .get("http://localhost:8080/test/admin")
            .header("Cookie", "JSESSIONID=$jSessionId")
            .build()

        val getResponseEntity = restTemplate.exchange(getRequest, String::class.java)
        println(getResponseEntity.body)
    }
}