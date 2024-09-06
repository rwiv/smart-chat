package com.github.smartchat.domaincore.domain.chatuser

import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery

data class ChatUserQuery(
    val account: Boolean,
    val chatRoom: ChatRoomQuery?,
)
