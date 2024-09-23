package com.github.smartchat.appapi.domain.chatroom

data class ChatRoomAddReq(
    val title: String,
    val password: String? = null,
)