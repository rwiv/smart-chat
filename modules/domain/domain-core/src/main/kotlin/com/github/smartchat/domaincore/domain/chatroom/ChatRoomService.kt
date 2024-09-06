package com.github.smartchat.domaincore.domain.chatroom

import org.springframework.stereotype.Service

@Service
class ChatRoomService(
    private val chatRoomRepository: ChatRoomRepository,
) {
    fun create(req: ChatRoomAdd): ChatRoom {
        return chatRoomRepository.add(req, ChatRoomQuery(createdBy = true))
    }

    fun findAll(): List<ChatRoom> {
        return chatRoomRepository.findAll(ChatRoomQuery(createdBy = true))
    }
}
