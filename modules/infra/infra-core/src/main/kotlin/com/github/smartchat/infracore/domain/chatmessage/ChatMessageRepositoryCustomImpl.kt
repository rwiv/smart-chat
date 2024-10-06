package com.github.smartchat.infracore.domain.chatmessage

import com.github.smartchat.infracore.domain.chatroom.ChatRoomEnt
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class ChatMessageRepositoryCustomImpl(
    private val em: EntityManager,
) : ChatMessageRepositoryCustom {

    override fun findByDescOffset(chatRoom: ChatRoomEnt, page: Int, size: Int, offset: Int): List<ChatMessageEnt> {
        val off = (page * size) + offset
        val query = em.createQuery(
            "SELECT cm FROM ChatMessageEnt cm " +
                    "WHERE cm.chatRoom.id = ${chatRoom.id} " +
                    "ORDER BY cm.num DESC " +
                    "LIMIT $size OFFSET $off",
            ChatMessageEnt::class.java
        )

        return query.resultList
    }
}
