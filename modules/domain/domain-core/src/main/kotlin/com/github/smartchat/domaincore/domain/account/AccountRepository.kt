package com.github.smartchat.domaincore.domain.account

import java.util.*

interface AccountRepository {
    fun findAll(): List<Account>
    fun findById(id: UUID): Account?
    fun findByUsername(username: String): Account?
    fun add(req: AccountAdd): Account
}