package com.github.smartchat.domaincore.domain.chatuser

import com.github.smartchat.domaincore.domain.account.AccountPublic
import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import java.time.LocalDateTime
import java.util.*

data class ChatUser(
    val id: UUID,
    val accountId: UUID,
    val account: AccountPublic?,
    val chatRoomId: UUID,
    val chatRoom: ChatRoom?,
    val createdAt: LocalDateTime,
)
