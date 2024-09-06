package com.github.smartchat.domaincore.domain.chatuser

import java.util.*

interface ChatUserRepository {
    fun findAll(query: ChatUserQuery): List<ChatUser>
    fun findById(id: UUID, query: ChatUserQuery): ChatUser?
    fun add(req: ChatUserAdd, query: ChatUserQuery): ChatUser
}