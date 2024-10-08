package com.github.smartchat.infracore.domain.chatmessage

import com.github.smartchat.commonutils.exceptions.HttpException
import com.github.smartchat.infracore.domain.chatroom.ChatRoomEnt
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class ChatMessageRepositoryCustomImpl(
    private val em: EntityManager,
) : ChatMessageRepositoryCustom {

    override fun findByDescOffset(chatRoom: ChatRoomEnt, page: Int, size: Int, offset: Int): List<ChatMessageEnt> {
        if (page < 0) throw HttpException(400, "invalid page number")

        val off = (page * size) + offset
        val query = em.createQuery(
            "SELECT cm FROM ChatMessageEnt cm " +
                    "WHERE cm.chatRoom = :chatRoom " +
                    "ORDER BY cm.num DESC " +
                    "LIMIT $size OFFSET $off",
            ChatMessageEnt::class.java
        )
        query.setParameter("chatRoom", chatRoom)

        return query.resultList
    }
}
