package com.github.smartchat.domaincore.domain.account

import java.util.*

data class AccountPublic(
    val id: UUID,
    val role: AccountRole,
    val username: String,
    val nickname: String,
    val avatarUrl: String?,
)