package com.github.smartchat.infracore.domain.chatuser

import com.github.smartchat.commonutils.exceptions.HttpException
import com.github.smartchat.domaincore.domain.account.Account
import com.github.smartchat.domaincore.domain.chatuser.ChatUser
import com.github.smartchat.domaincore.domain.chatuser.ChatUserAddInfra
import com.github.smartchat.domaincore.domain.chatuser.ChatUserQuery
import com.github.smartchat.domaincore.domain.chatuser.ChatUserRepository
import com.github.smartchat.infracore.domain.account.AccountMapper
import com.github.smartchat.infracore.domain.chatroom.ChatRoomEnt
import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Repository
class ChatUserRepositoryImpl(
    private val chatUserJpaRepository: ChatUserJpaRepository,
    private val chatUserMapper: ChatUserMapper,
    private val accountMapper: AccountMapper,
) : ChatUserRepository {

    @Transactional
    override fun add(req: ChatUserAddInfra, query: ChatUserQuery): ChatUser {
        return chatUserJpaRepository.save(chatUserMapper.addToEnt(req))
            .let { chatUserMapper.entToDto(it, query) }
    }

    @Transactional
    override fun delete(id: UUID, query: ChatUserQuery) {
        chatUserJpaRepository.delete(ChatUserEnt.onlyId(id))
    }

    override fun findAll(query: ChatUserQuery): List<ChatUser> {
        return chatUserJpaRepository.findAll()
            .map { chatUserMapper.entToDto(it, query) }
    }

    override fun findByPage(page: Int, size: Int, query: ChatUserQuery): List<ChatUser> {
        if (page < 0) throw HttpException(400, "invalid page number")

        return chatUserJpaRepository.findAll(PageRequest.of(page, size)).content
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

    override fun findByChatRoom(chatRoomId: UUID, query: ChatUserQuery): List<ChatUser> {
        return chatUserJpaRepository.findByChatRoom(ChatRoomEnt.onlyId(chatRoomId))
            .map { chatUserMapper.entToDto(it, query) }
    }
}