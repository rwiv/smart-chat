package com.github.smartchat.appapi.helpers

import com.github.smartchat.domaincore.domain.chatroom.ChatRoomAdd
import java.util.UUID

fun cr(createdById: UUID, title: String, isPrivate: Boolean = false, password: String? = null) = ChatRoomAdd(
    createdById = createdById,
    title = title,
    isPrivate = isPrivate,
    password = password,
)
