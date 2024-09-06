package com.github.smartchat.infracore.domain.account

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AccountJpaRepository : JpaRepository<AccountEnt, UUID> {
    fun findByUsername(username: String): AccountEnt?
}