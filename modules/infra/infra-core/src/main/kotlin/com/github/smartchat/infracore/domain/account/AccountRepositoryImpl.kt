package com.github.smartchat.infracore.domain.account

import com.github.smartchat.domaincore.domain.account.Account
import com.github.smartchat.domaincore.domain.account.AccountAdd
import com.github.smartchat.domaincore.domain.account.AccountRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Repository
class AccountRepositoryImpl(
    private val accountJpaRepository: AccountJpaRepository,
    private val accountMapper: AccountMapper,
) : AccountRepository {

    override fun findAll(): List<Account> {
        return accountJpaRepository.findAll()
            .map { accountMapper.entToDto(it) }
    }

    override fun findById(id: UUID): Account? {
        return accountJpaRepository.findById(id)
            .getOrNull()?.let { accountMapper.entToDto(it) }
    }

    override fun findByUsername(username: String): Account? {
        return accountJpaRepository.findByUsername(username)
            ?.let { accountMapper.entToDto(it) }
    }

    @Transactional
    override fun add(req: AccountAdd): Account {
        return accountJpaRepository.save(accountMapper.addToEnt(req))
            .let { accountMapper.entToDto(it) }
    }
}