package com.github.smartchat.controller

import com.github.smartchat.domaincore.domain.account.AccountPublic
import com.github.smartchat.domaincore.domain.account.AccountService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
    private val accountService: AccountService
) {

    @GetMapping("/accounts")
    fun findByAll(): List<AccountPublic> {
        return accountService.findAll().map { it.toPublic() }
    }
}