package com.github.smartchat.infracore.domain.chatuser

import com.github.smartchat.domaincore.domain.account.Account
import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import com.github.smartchat.domaincore.domain.chatuser.ChatUser
import com.github.smartchat.domaincore.domain.chatuser.ChatUserAdd
import com.github.smartchat.domaincore.domain.chatuser.ChatUserQuery
import com.github.smartchat.domaincore.domain.chatuser.ChatUserRepository
import com.github.smartchat.infracore.domain.account.AccountMapper
import com.github.smartchat.infracore.domain.chatroom.ChatRoomMapper
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Repository
class ChatUserRepositoryImpl(
    private val chatUserJpaRepository: ChatUserJpaRepository,
    private val chatUserMapper: ChatUserMapper,
    private val accountMapper: AccountMapper,
    private val chatRoomMapper: ChatRoomMapper,
) : ChatUserRepository {

    override fun findAll(query: ChatUserQuery): List<ChatUser> {
        return chatUserJpaRepository.findAll()
            .map { chatUserMapper.entToDto(it, query) }
    }

    override fun findById(id: UUID, query: ChatUserQuery): ChatUser? {
        return chatUserJpaRepository.findById(id).getOrNull()
            ?.let { chatUserMapper.entToDto(it, query) }
    }

    override fun findByAccount(account: Account, query: ChatUserQuery): List<ChatUser> {
        return chatUserJpaRepository.findByAccount(accountMapper.dtoToEnt(account))
            .map { chatUserMapper.entToDto(it, query) }
    }

    override fun findByChatRoom(chatRoom: ChatRoom, query: ChatUserQuery): List<ChatUser> {
        val crQuery = query.chatRoom ?: ChatRoomQuery(false)
        return chatUserJpaRepository.findByChatRoom(chatRoomMapper.dtoToEnt(chatRoom, crQuery))
            .map { chatUserMapper.entToDto(it, query) }
    }

    @Transactional
    override fun add(req: ChatUserAdd, query: ChatUserQuery): ChatUser {
        return chatUserJpaRepository.save(chatUserMapper.addToEnt(req))
            .let { chatUserMapper.entToDto(it, query) }
    }
}