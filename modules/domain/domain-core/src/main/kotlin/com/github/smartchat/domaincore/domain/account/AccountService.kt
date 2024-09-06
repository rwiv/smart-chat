package com.github.smartchat.domaincore.domain.account

import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
) {
    fun create(req: AccountAdd): Account {
        return accountRepository.add(req)
    }

    fun findAll() : List<Account> {
        return accountRepository.findAll()
    }
}