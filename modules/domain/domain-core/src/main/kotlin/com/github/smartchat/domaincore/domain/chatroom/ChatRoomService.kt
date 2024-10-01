package com.github.smartchat.domaincore.domain.chatroom

import com.github.smartchat.commonutils.exceptions.HttpException
import com.github.smartchat.commonutils.exceptions.NotFoundException
import com.github.smartchat.domaincore.domain.chatuser.ChatUserAddInfra
import com.github.smartchat.domaincore.domain.chatuser.ChatUserQuery
import com.github.smartchat.domaincore.domain.chatuser.ChatUserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatRoomService(
    private val chatRoomRepository: ChatRoomRepository,
    private val chatUserRepository: ChatUserRepository,
) {

    @Transactional
    fun create(req: ChatRoomAdd, query: ChatRoomQuery = ChatRoomQuery(createdBy = true)): ChatRoom {
        // create ChatRoom
        val chatRoom = chatRoomRepository.add(req, query)

        // add creator ChatUser to ChatRoom
        chatUserRepository.add(ChatUserAddInfra(
            accountId = req.createdById,
            chatRoomId = chatRoom.id,
        ), ChatUserQuery(false, null))

        // increase user count
        chatRoomRepository.updateUserCnt(chatRoom.id, true, ChatRoomQuery(createdBy = false))

        return chatRoom
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

    @Transactional
    fun updateSharedChatUser(chatRoomId: UUID, requesterId: UUID, isClose: Boolean, query: ChatRoomQuery): ChatRoom {
        val chatUserId = if (isClose) { null } else {
            chatUserRepository.findByChatRoom(chatRoomId, ChatUserQuery(true, null))
                .find { it.accountId == requesterId }?.id
                ?: throw NotFoundException("chatUser not found")
        }
        return chatRoomRepository.updateSharedChatUser(chatRoomId, chatUserId, query)
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
