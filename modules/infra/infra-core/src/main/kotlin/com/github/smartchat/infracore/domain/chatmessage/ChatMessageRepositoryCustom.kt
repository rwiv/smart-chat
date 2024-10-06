package com.github.smartchat.infracore.domain.chatmessage

import com.github.smartchat.infracore.domain.chatroom.ChatRoomEnt

interface ChatMessageRepositoryCustom {

    fun findByDescOffset(chatRoom: ChatRoomEnt, page: Int, size: Int, offset: Int): List<ChatMessageEnt>
}
