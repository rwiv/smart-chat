package com.github.smartchat.appapi.domain.chatroom

import java.util.*

data class ChatMessageAddReq(
    val content: String,
    val chatRoomId: UUID,
)
