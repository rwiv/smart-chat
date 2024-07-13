package com.github.smartchat.infracore.domain.chatroom

import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomRepository
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomAdd
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Repository
class ChatRoomRepositoryImpl(
    private val chatRoomJpaRepository: ChatRoomJpaRepository,
) : ChatRoomRepository {

    override fun findAll(): List<ChatRoom> {
        return chatRoomJpaRepository.findAll().map { it.toChatRoom() }
    }

    override fun findById(id: UUID): ChatRoom? {
        return chatRoomJpaRepository.findById(id).getOrNull()?.toChatRoom()
    }

    override fun add(chatRoomAdd: ChatRoomAdd): ChatRoom {
        val chatRoom = ChatRoomEntity(title = chatRoomAdd.title)
        return chatRoomJpaRepository.save(chatRoom).toChatRoom()
    }
}