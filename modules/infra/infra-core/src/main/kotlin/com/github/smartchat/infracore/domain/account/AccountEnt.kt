package com.github.smartchat.infracore.domain.account

import com.github.smartchat.domaincore.domain.account.AccountRole
import com.github.smartchat.infracore.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.time.LocalDateTime
import java.util.*

@Entity
class AccountEnt(
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: AccountRole,

    @Column(unique = true)
    val username: String,

    @Column
    val password: String,

    @Column(unique = true)
    val nickname: String,

    @Column
    val avatarUrl: String?,

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime,

    id: UUID? = null,
) : BaseEntity(id) {

    companion object {
        fun onlyId(id: UUID): AccountEnt {
            return AccountEnt(
                id = id,
                role = AccountRole.MEMBER,
                username = "",
                password = "",
                nickname = "",
                avatarUrl = "",
                createdAt = LocalDateTime.now()
            )
        }
    }
}