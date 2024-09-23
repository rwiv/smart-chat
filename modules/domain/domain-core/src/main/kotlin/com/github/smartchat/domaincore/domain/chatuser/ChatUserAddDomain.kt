package com.github.smartchat.domaincore.domain.chatuser

import java.util.*

data class ChatUserAddDomain(
    val chatRoomId: UUID,
    val accountId: UUID,
    val password: String?,
)