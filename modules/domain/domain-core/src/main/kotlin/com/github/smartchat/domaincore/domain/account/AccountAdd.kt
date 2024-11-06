package com.github.smartchat.domaincore.domain.account

data class AccountAdd(
    val role: AccountRole,
    val username: String,
    var password: String,
    val nickname: String,
    var avatarUrl: String? = null,
)
