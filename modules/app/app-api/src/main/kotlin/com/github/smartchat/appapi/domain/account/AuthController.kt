package com.github.smartchat.appapi.domain.account

import com.github.smartchat.domaincore.domain.account.AccountAdd
import com.github.smartchat.domaincore.domain.account.AccountPublic
import com.github.smartchat.domaincore.domain.account.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val accountService: AccountService,
) {

    @PostMapping("/signup")
    fun signup(@RequestBody accountCreation: AccountAdd): ResponseEntity<AccountPublic> {
        val account = accountService.create(accountCreation).toPublic()
        return ResponseEntity.ok().body(account)
    }
}
