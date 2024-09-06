package com.github.smartchat.domaincore.domain.chatuser

import java.util.*

data class ChatUserAdd(
    val accountId: UUID,
    val chatRoomId: UUID,
)
