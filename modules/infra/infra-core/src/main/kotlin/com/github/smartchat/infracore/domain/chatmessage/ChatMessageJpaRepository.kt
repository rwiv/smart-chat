package com.github.smartchat.infracore.domain.chatmessage

import com.github.smartchat.infracore.domain.chatroom.ChatRoomEnt
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ChatMessageJpaRepository : JpaRepository<ChatMessageEnt, UUID>, ChatMessageRepositoryCustom {

    fun findByChatRoom(chatRoom: ChatRoomEnt): List<ChatMessageEnt>

    @Query("SELECT cm FROM ChatMessageEnt cm " +
            "WHERE cm.chatRoom = :chatRoom " +
            "ORDER BY cm.num DESC " +
            "LIMIT 1")
    fun findLatestOne(chatRoom: ChatRoomEnt): ChatMessageEnt?
}
