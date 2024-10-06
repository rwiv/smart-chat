package com.github.smartchat.domaincore.domain.chatmessage

import java.util.*

data class ChatMessageAdd(
    val content: String,
    val createdById: UUID,
    val chatRoomId: UUID,
)
