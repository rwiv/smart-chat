package com.github.smartchat.domaincore.domain.chatroom

import com.github.smartchat.commonutils.exceptions.HttpException
import com.github.smartchat.commonutils.exceptions.NotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatRoomService(
    private val chatRoomRepository: ChatRoomRepository,
) {

    @Transactional
    fun create(req: ChatRoomAdd, query: ChatRoomQuery = ChatRoomQuery(createdBy = true)): ChatRoom {
        return chatRoomRepository.add(req, query)
    }

    @Transactional
    fun delete(id: UUID, requesterId: UUID, query: ChatRoomQuery): ChatRoom {
        val chatRoom = findById(id, query) ?: throw NotFoundException("chatRoom not found")
        if (chatRoom.createdById != requesterId) {
            throw HttpException(403, "requester is not the creator of the chatRoom")
        }
        chatRoomRepository.delete(chatRoom.id, query)
        return chatRoom
    }

    fun findAll(query: ChatRoomQuery): List<ChatRoom> {
        return chatRoomRepository.findAll(query)
    }

    fun findById(id: UUID, query: ChatRoomQuery): ChatRoom? {
        return chatRoomRepository.findById(id, query)
    }

    fun findByPage(page: Int, size: Int, query: ChatRoomQuery): List<ChatRoom> {
        return chatRoomRepository.findByPage(page, size, query)
    }
}
