package com.github.smartchat.domaincore.domain.chatuser

import java.util.*

data class ChatUserAddInfra(
    val accountId: UUID,
    val chatRoomId: UUID,
)
