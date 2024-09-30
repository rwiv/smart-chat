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
        req.avatarUrl = "/avatars/${getRandInt()}.svg"
        return accountRepository.add(req)
    }

    private fun getRandInt(): Int {
        val list = ArrayList<Int>()
        for (i in 0..99) {
            list.add(i)
        }
        return list.random()
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