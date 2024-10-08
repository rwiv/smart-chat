package com.github.smartchat.infracore.domain.chatmessage

import com.github.smartchat.commonutils.exceptions.HttpException
import com.github.smartchat.commonutils.exceptions.NotFoundException
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessage
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessageAdd
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessageQuery
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessageRepository
import com.github.smartchat.infracore.domain.account.AccountJpaRepository
import com.github.smartchat.infracore.domain.chatroom.ChatRoomJpaRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Repository
class ChatMessageRepositoryImpl(
    private val chatMessageJpaRepository: ChatMessageJpaRepository,
    private val chatRoomJpaRepository: ChatRoomJpaRepository,
    private val accountJpaRepository: AccountJpaRepository,
    private val chatMessageMapper: ChatMessageMapper,
) : ChatMessageRepository {

    @Transactional
    override fun add(req: ChatMessageAdd, query: ChatMessageQuery): ChatMessage {
        val chatRoom = chatRoomJpaRepository.findById(req.chatRoomId).getOrNull()
            ?: throw NotFoundException("not found chatroom")

        val createdBy = accountJpaRepository.findById(req.createdById).getOrNull()
            ?: throw NotFoundException("not found account")

        val latest = chatMessageJpaRepository.findLatestOne(chatRoom)
        val num = if (latest == null) 0 else latest.num + 1

        return chatMessageJpaRepository.save(
            chatMessageMapper.addToEnt(req, chatRoom, createdBy, num)
        ).let {
            chatMessageMapper.entToDto(it, query)
        }
    }

    override fun findById(id: UUID, query: ChatMessageQuery): ChatMessage? {
        return chatMessageJpaRepository.findById(id).getOrNull()
            ?.let { chatMessageMapper.entToDto(it, query) }
    }

    override fun findAll(query: ChatMessageQuery): List<ChatMessage> {
        return chatMessageJpaRepository.findAll()
            .map { chatMessageMapper.entToDto(it, query) }
    }

    override fun findByPage(page: Int, size: Int, query: ChatMessageQuery): List<ChatMessage> {
        if (page < 0) throw HttpException(400, "invalid page number")

        return chatMessageJpaRepository.findAll(PageRequest.of(page, size)).content
            .map { chatMessageMapper.entToDto(it, query) }
    }

    override fun findByChatRoom(chatRoomId: UUID, query: ChatMessageQuery): List<ChatMessage> {
        val chatRoom = chatRoomJpaRepository.findById(chatRoomId).getOrNull()
            ?: throw NotFoundException("not found chatroom")

        return chatMessageJpaRepository.findByChatRoom(chatRoom)
            .map { chatMessageMapper.entToDto(it, query) }
    }

    override fun findByDescOffset(
        chatRoomId: UUID,
        page: Int, size: Int, offset: Int,
        query: ChatMessageQuery
    ): List<ChatMessage> {
        val chatRoom = chatRoomJpaRepository.findById(chatRoomId).getOrNull()
            ?: throw NotFoundException("not found chatroom")

        return chatMessageJpaRepository.findByDescOffset(chatRoom, page, size, offset)
            .map { chatMessageMapper.entToDto(it, query) }
    }
}
