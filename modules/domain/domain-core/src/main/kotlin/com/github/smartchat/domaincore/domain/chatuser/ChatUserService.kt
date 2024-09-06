package com.github.smartchat.domaincore.domain.chatuser

import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import org.springframework.stereotype.Service

@Service
class ChatUserService(
    private val chatUserRepository: ChatUserRepository,
) {
    fun create(req: ChatUserAdd): ChatUser {
        val query = ChatUserQuery(account = true, chatRoom = ChatRoomQuery(createdBy = false))
        return chatUserRepository.add(req, query)
    }

    fun findAll() : List<ChatUser> {
        val query = ChatUserQuery(account = true, chatRoom = ChatRoomQuery(createdBy = false))
        return chatUserRepository.findAll(query)
    }
}