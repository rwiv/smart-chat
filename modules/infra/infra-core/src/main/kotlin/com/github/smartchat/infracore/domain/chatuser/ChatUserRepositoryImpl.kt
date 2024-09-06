package com.github.smartchat.infracore.domain.chatuser

import com.github.smartchat.domaincore.domain.chatuser.ChatUser
import com.github.smartchat.domaincore.domain.chatuser.ChatUserAdd
import com.github.smartchat.domaincore.domain.chatuser.ChatUserQuery
import com.github.smartchat.domaincore.domain.chatuser.ChatUserRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Repository
class ChatUserRepositoryImpl(
    private val chatUserJpaRepository: ChatUserJpaRepository,
    private val chatUserMapper: ChatUserMapper,
) : ChatUserRepository {

    override fun findAll(query: ChatUserQuery): List<ChatUser> {
        return chatUserJpaRepository.findAll()
            .map { chatUserMapper.entToDto(it, query) }
    }

    override fun findById(id: UUID, query: ChatUserQuery): ChatUser? {
        return chatUserJpaRepository.findById(id).getOrNull()
            ?.let { chatUserMapper.entToDto(it, query) }
    }

    @Transactional
    override fun add(req: ChatUserAdd, query: ChatUserQuery): ChatUser {
        return chatUserJpaRepository.save(chatUserMapper.addToEnt(req))
            .let { chatUserMapper.entToDto(it, query) }
    }
}