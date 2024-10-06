package com.github.smartchat.domaincore.domain.chatmessage

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChatMessageService(
    private val chatMessageRepository: ChatMessageRepository,
) {

    @Transactional
    fun create(chatMessageAdd: ChatMessageAdd, query: ChatMessageQuery): ChatMessage {
        return chatMessageRepository.add(chatMessageAdd, query)
    }

    fun findAll(query: ChatMessageQuery): List<ChatMessage> {
        return chatMessageRepository.findAll(query)
    }

    fun findById(id: UUID, query: ChatMessageQuery): ChatMessage? {
        return chatMessageRepository.findById(id, query)
    }

    fun findByPage(chatRoomId: UUID, page: Int, size: Int, offset: Int, query: ChatMessageQuery): List<ChatMessage> {
        return chatMessageRepository.findByDescOffset(chatRoomId, page, size, offset, query)
    }
}
