package com.github.smartchat.domaincore.domain.chatroom

import java.util.UUID

data class ChatRoom(
    val id: UUID,
    val title: String,
)