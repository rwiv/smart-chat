package com.github.smartchat.domaincore.domain.chatroom

import java.util.UUID

interface ChatRoomRepository {
    fun findAll(query: ChatRoomQuery): List<ChatRoom>
    fun findById(id: UUID, query: ChatRoomQuery): ChatRoom?
    fun add(req: ChatRoomAdd, query: ChatRoomQuery): ChatRoom
}