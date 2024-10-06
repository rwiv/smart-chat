package com.github.smartchat.domaincore.domain.chatmessage

import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery

data class ChatMessageQuery(
    val createdBy: Boolean,
    val chatRoom: ChatRoomQuery?,
)
