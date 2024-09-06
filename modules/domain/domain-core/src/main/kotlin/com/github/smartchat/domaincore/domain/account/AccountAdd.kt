package com.github.smartchat.domaincore.domain.account

data class AccountAdd(
    val role: AccountRole,
    val username: String,
    val password: String,
    val nickname: String,
    val avatarUrl: String? = null,
)
