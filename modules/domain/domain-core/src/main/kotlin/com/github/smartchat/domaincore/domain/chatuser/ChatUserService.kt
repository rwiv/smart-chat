package com.github.smartchat.domaincore.domain.chatuser

import com.github.smartchat.commonutils.exceptions.NotFoundException
import com.github.smartchat.domaincore.domain.account.AccountRepository
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChatUserService(
    private val chatUserRepository: ChatUserRepository,
    private val accountRepository: AccountRepository,
) {
    fun create(req: ChatUserAdd): ChatUser {
        val query = ChatUserQuery(account = true, chatRoom = ChatRoomQuery(createdBy = false))
        return chatUserRepository.add(req, query)
    }

    fun findAll() : List<ChatUser> {
        val query = ChatUserQuery(account = true, chatRoom = ChatRoomQuery(createdBy = false))
        return chatUserRepository.findAll(query)
    }

    fun findByAccountId(accountId: UUID, query: ChatUserQuery): List<ChatUser> {
        val account = accountRepository.findById(accountId) ?: throw NotFoundException("Account not found")
        return chatUserRepository.findByAccount(account, query)
    }
}