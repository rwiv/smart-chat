package com.github.smartchat.domaincore.domain.chatuser

import com.github.smartchat.domaincore.domain.account.Account
import java.util.*

interface ChatUserRepository {
    fun add(req: ChatUserAddInfra, query: ChatUserQuery): ChatUser
    fun delete(id: UUID, query: ChatUserQuery)
    fun findAll(query: ChatUserQuery): List<ChatUser>
    fun findByPage(page: Int, size: Int, query: ChatUserQuery): List<ChatUser>
    fun findById(id: UUID, query: ChatUserQuery): ChatUser?
    fun findByAccount(account: Account, query: ChatUserQuery): List<ChatUser>
    fun findByChatRoom(chatRoomId: UUID, query: ChatUserQuery): List<ChatUser>
}