package com.github.smartchat.domaincore.domain.chatroom

import java.util.*

data class ChatRoomAdd(
    val title: String,
    val createdById: UUID,
    val isPrivate: Boolean,
    val password: String? = null,
)