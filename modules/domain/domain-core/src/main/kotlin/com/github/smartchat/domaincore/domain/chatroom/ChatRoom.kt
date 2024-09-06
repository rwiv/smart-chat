package com.github.smartchat.domaincore.domain.chatroom

import com.github.smartchat.domaincore.domain.account.AccountPublic
import java.time.LocalDateTime
import java.util.UUID

data class ChatRoom(
    val id: UUID,
    val title: String,
    val createdById: UUID,
    val createdBy: AccountPublic?,
    val createdAt: LocalDateTime,
    val password: String?,
    val isPrivate: Boolean,
    val userCnt: Int,
)