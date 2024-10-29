package com.github.smartchat.domaincore.domain.account

import java.time.LocalDateTime
import java.util.*

data class Account(
    val id: UUID,
    val role: AccountRole,
    val username: String,
    val password: String,
    val nickname: String,
    val avatarUrl: String?,
    val createdAt: LocalDateTime,
) {
    fun toPublic() = AccountPublic(
        id = id,
        role = role,
        username = username,
        nickname = nickname,
        avatarUrl = avatarUrl,
        createdAt = createdAt,
    )
}
