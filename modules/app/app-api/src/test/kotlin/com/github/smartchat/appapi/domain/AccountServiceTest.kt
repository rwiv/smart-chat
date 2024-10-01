package com.github.smartchat.appapi.domain

import com.github.smartchat.domaincore.domain.account.AccountService
import com.github.smartchat.appapi.helpers.ac
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AccountServiceTest(
    @Autowired private val accountService: AccountService,
) {

    @Transactional
    @Test fun test() {
        val ac1 = accountService.create(ac("a"))
        println(ac1)
    }
}
