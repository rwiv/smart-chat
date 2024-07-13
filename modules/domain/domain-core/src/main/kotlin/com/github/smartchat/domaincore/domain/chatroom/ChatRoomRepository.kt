package com.github.smartchat.domaincore.domain.chatroom

import java.util.UUID

interface ChatRoomRepository {
    fun findAll(): List<ChatRoom>
    fun findById(id: UUID): ChatRoom?
    fun add(chatRoomAdd: ChatRoomAdd): ChatRoom
}