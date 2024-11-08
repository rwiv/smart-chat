package com.github.smartchat.infracore.domain.chatroom

import com.github.smartchat.commonutils.exceptions.HttpException
import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomAdd
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomRepository
import com.github.smartchat.infracore.domain.chatuser.ChatUserJpaRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Repository
class ChatRoomRepositoryImpl(
    private val chatRoomJpaRepository: ChatRoomJpaRepository,
    private val chatUserJpaRepository: ChatUserJpaRepository,
    private val chatRoomMapper: ChatRoomMapper,
) : ChatRoomRepository {

    @Transactional
    override fun add(req: ChatRoomAdd, query: ChatRoomQuery): ChatRoom {
        return chatRoomJpaRepository.save(chatRoomMapper.addToEnt(req))
            .let { chatRoomMapper.entToDto(it, query) }
    }

    @Transactional
    override fun delete(chatRoomId: UUID, query: ChatRoomQuery) {
        chatRoomJpaRepository.delete(ChatRoomEnt.onlyId(chatRoomId))
    }

    @Transactional
    override fun updateUserCnt(chatRoomId: UUID, isPlus: Boolean, query: ChatRoomQuery) {
        val chatRoomEnt = chatRoomJpaRepository.findById(chatRoomId).getOrNull()
            ?: throw HttpException(404, "chatRoom not found")

        if (isPlus) {
            chatRoomEnt.userCnt += 1
        } else {
            chatRoomEnt.userCnt -= 1
        }
        chatRoomJpaRepository.save(chatRoomEnt)
    }

    @Transactional
    override fun updateSharedChatUser(chatRoomId: UUID, sharedChatUserId: UUID?, query: ChatRoomQuery): ChatRoom {
        val chatRoomEnt = chatRoomJpaRepository.findById(chatRoomId).getOrNull()
            ?: throw HttpException(404, "chatRoom not found")

        val chatUser = sharedChatUserId?.let {
            chatUserJpaRepository.findById(it).getOrNull()
        }
        chatRoomEnt.sharedChatUser = chatUser
        chatRoomJpaRepository.save(chatRoomEnt)

        return chatRoomMapper.entToDto(chatRoomEnt, query)
    }

    override fun findAll(query: ChatRoomQuery): List<ChatRoom> {
        return chatRoomJpaRepository.findAll()
            .map { chatRoomMapper.entToDto(it, query) }
    }

    override fun findById(id: UUID, query: ChatRoomQuery): ChatRoom? {
        return chatRoomJpaRepository.findById(id)
            .getOrNull()?.let { chatRoomMapper.entToDto(it, query) }
    }

    override fun findByPage(page: Int, size: Int, query: ChatRoomQuery): List<ChatRoom> {
        if (page < 0) throw HttpException(400, "invalid page number")

        return chatRoomJpaRepository.findAll(PageRequest.of(page, size)).content
            .map { chatRoomMapper.entToDto(it, query) }
    }
}