package com.github.smartchat.domaincore.domain.chatmessage

import com.github.smartchat.domaincore.domain.account.AccountPublic
import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import java.time.LocalDateTime
import java.util.*

data class ChatMessage(
    val id: UUID,
    val content: String,
    val createdById: UUID,
    val createdBy: AccountPublic?,
    val createdAt: LocalDateTime,
    val chatRoomId: UUID,
    val chatRoom: ChatRoom?,
    val num: Int,
)
