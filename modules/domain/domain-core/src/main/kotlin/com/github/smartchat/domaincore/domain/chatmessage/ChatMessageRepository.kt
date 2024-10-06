package com.github.smartchat.domaincore.domain.chatmessage

import java.util.*

interface ChatMessageRepository {
    fun add(req: ChatMessageAdd, query: ChatMessageQuery): ChatMessage
    fun findById(id: UUID, query: ChatMessageQuery): ChatMessage?
    fun findAll(query: ChatMessageQuery): List<ChatMessage>
    fun findByPage(page: Int, size: Int, query: ChatMessageQuery): List<ChatMessage>
    fun findByChatRoom(chatRoomId: UUID, query: ChatMessageQuery): List<ChatMessage>
    fun findByDescOffset(chatRoomId: UUID, page: Int, size: Int, offset: Int, query: ChatMessageQuery): List<ChatMessage>
}