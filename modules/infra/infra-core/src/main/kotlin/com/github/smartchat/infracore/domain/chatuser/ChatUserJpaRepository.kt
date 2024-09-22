package com.github.smartchat.infracore.domain.chatuser

import com.github.smartchat.infracore.domain.account.AccountEnt
import com.github.smartchat.infracore.domain.chatroom.ChatRoomEnt
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChatUserJpaRepository : JpaRepository<ChatUserEnt, UUID> {
    fun findByAccount(account: AccountEnt): List<ChatUserEnt>
    fun findByChatRoom(chatRoom: ChatRoomEnt): List<ChatUserEnt>
}
