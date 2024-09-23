package com.github.smartchat.domaincore.domain.account

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountService(
    private val accountRepository: AccountRepository,
) {

    @Transactional
    fun create(req: AccountAdd): Account {
        return accountRepository.add(req)
    }

    fun findAll() : List<Account> {
        return accountRepository.findAll()
    }

    fun findById(id: UUID): Account? {
        return accountRepository.findById(id)
    }

    fun findByUsername(username: String): Account? {
        return accountRepository.findByUsername(username)
    }
}