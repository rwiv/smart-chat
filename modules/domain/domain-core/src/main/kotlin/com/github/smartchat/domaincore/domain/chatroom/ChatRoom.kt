package com.github.smartchat.domaincore.domain.chatroom

import com.github.smartchat.domaincore.domain.account.AccountPublic
import com.github.smartchat.domaincore.domain.chatuser.ChatUser
import java.time.LocalDateTime
import java.util.UUID

data class ChatRoom(
    val id: UUID,
    val title: String,
    val createdById: UUID,
    val createdBy: AccountPublic?,
    val createdAt: LocalDateTime,
    val userCnt: Int,

    val password: String?,
    val isPrivate: Boolean,

    val sharedChatUserId: UUID? = null,
)