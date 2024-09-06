package com.github.smartchat.infracore.domain.account

import com.github.smartchat.domaincore.domain.account.AccountRole
import com.github.smartchat.infracore.common.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

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
) : BaseTimeEntity()