package com.github.smartchat.infracore.domain.chatmessage

import com.github.smartchat.domaincore.domain.chatmessage.ChatMessage
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessageAdd
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessageQuery
import com.github.smartchat.infracore.domain.account.AccountEnt
import com.github.smartchat.infracore.domain.account.AccountMapper
import com.github.smartchat.infracore.domain.chatroom.ChatRoomEnt
import com.github.smartchat.infracore.domain.chatroom.ChatRoomMapper
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ChatMessageMapper(
    private val accountMapper: AccountMapper,
    private val chatRoomMapper: ChatRoomMapper,
) {

    fun entToDto(ent: ChatMessageEnt, query: ChatMessageQuery): ChatMessage {
        val createdBy = if (query.createdBy) {
            accountMapper.entToDto(ent.createdBy).toPublic()
        } else null
        val chatRoom = query.chatRoom?.let {
            chatRoomMapper.entToDto(ent.chatRoom, it)
        }

        return ChatMessage(
            id = ent.id ?: throw NullPointerException("id is null"),
            chatRoomId = ent.chatRoom.id ?: throw NullPointerException("chatRoom.id is null"),
            chatRoom = chatRoom,
            content = ent.content,
            createdById = ent.createdBy.id ?: throw NullPointerException("createdBy.id is null"),
            createdBy = createdBy,
            createdAt = ent.createdAt,
            num = ent.num,
        )
    }

    fun addToEnt(req: ChatMessageAdd, chatRoom: ChatRoomEnt, createdBy: AccountEnt, num: Int): ChatMessageEnt {
        val ent = ChatMessageEnt(
            chatRoom = chatRoom,
            createdBy = createdBy,
            content = req.content,
            num = num,
            createdAt = LocalDateTime.now(),
        )
        return ent
    }
}
