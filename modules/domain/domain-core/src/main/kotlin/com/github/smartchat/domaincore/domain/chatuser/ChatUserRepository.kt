package com.github.smartchat.domaincore.domain.chatuser

import com.github.smartchat.domaincore.domain.account.Account
import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import java.util.*

interface ChatUserRepository {
    fun findAll(query: ChatUserQuery): List<ChatUser>
    fun findById(id: UUID, query: ChatUserQuery): ChatUser?
    fun findByAccount(account: Account, query: ChatUserQuery): List<ChatUser>
    fun findByChatRoom(chatRoom: ChatRoom, query: ChatUserQuery): List<ChatUser>
    fun add(req: ChatUserAdd, query: ChatUserQuery): ChatUser
}