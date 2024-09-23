package com.github.smartchat.domaincore.domain.chatroom

import java.util.UUID

interface ChatRoomRepository {
    fun add(req: ChatRoomAdd, query: ChatRoomQuery): ChatRoom
    fun delete(chatRoomId: UUID, query: ChatRoomQuery)
    fun findAll(query: ChatRoomQuery): List<ChatRoom>
    fun findById(id: UUID, query: ChatRoomQuery): ChatRoom?
    fun findByPage(page: Int, size: Int, query: ChatRoomQuery): List<ChatRoom>
}