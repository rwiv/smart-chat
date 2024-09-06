package com.github.smartchat.infracore.domain.chatroom

import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomRepository
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomAdd
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import com.github.smartchat.domaincore.domain.chatuser.ChatUserAdd
import com.github.smartchat.infracore.domain.chatuser.ChatUserJpaRepository
import com.github.smartchat.infracore.domain.chatuser.ChatUserMapper
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Repository
class ChatRoomRepositoryImpl(
    private val chatRoomJpaRepository: ChatRoomJpaRepository,
    private val chatUserJpaRepository: ChatUserJpaRepository,
    private val chatRoomMapper: ChatRoomMapper,
    private val chatUserMapper: ChatUserMapper,
) : ChatRoomRepository {

    override fun findAll(query: ChatRoomQuery): List<ChatRoom> {
        return chatRoomJpaRepository.findAll()
            .map { chatRoomMapper.entToDto(it, query) }
    }

    override fun findById(id: UUID, query: ChatRoomQuery): ChatRoom? {
        return chatRoomJpaRepository.findById(id)
            .getOrNull()?.let { chatRoomMapper.entToDto(it, query) }
    }

    @Transactional
    override fun add(req: ChatRoomAdd, query: ChatRoomQuery): ChatRoom {
        val chatRoomEnt = chatRoomJpaRepository.save(chatRoomMapper.addToEnt(req))

        chatUserJpaRepository.save(chatUserMapper.addToEnt(ChatUserAdd(
            accountId = req.createdById,
            chatRoomId = chatRoomEnt.id!!,
        )))
        chatRoomEnt.userCnt += 1
        chatRoomEnt.createdAt = LocalDateTime.now()

        return chatRoomJpaRepository.save(chatRoomEnt)
            .let { chatRoomMapper.entToDto(it, query) }
    }
}