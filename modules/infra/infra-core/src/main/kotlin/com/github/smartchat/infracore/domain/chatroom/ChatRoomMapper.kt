package com.github.smartchat.infracore.domain.chatroom

import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomAdd
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import com.github.smartchat.infracore.domain.account.AccountEnt
import com.github.smartchat.infracore.domain.account.AccountMapper
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ChatRoomMapper(
    private val accountMapper: AccountMapper,
) {

    fun entToDto(ent: ChatRoomEnt, query: ChatRoomQuery): ChatRoom {
        val createdBy = if (query.createdBy) {
            accountMapper.entToDto(ent.createdBy).toPublic()
        } else null

        return ChatRoom(
            id = ent.id ?: throw NullPointerException("id is null"),
            title = ent.title,
            createdById = ent.createdBy.id ?: throw NullPointerException("createdById is null"),
            createdBy = createdBy,
            createdAt = ent.createdAt,
            password = ent.password,
            isPrivate = ent.isPrivate,
            userCnt = ent.userCnt,
            sharedChatUserId = ent.sharedChatUser?.id,
        )
    }

    fun addToEnt(req: ChatRoomAdd): ChatRoomEnt {
        val ent = ChatRoomEnt(
            title = req.title,
            createdBy = AccountEnt.onlyId(req.createdById),
            password = req.password,
            isPrivate = req.isPrivate,
            userCnt = 0,
            createdAt = LocalDateTime.now(),
        )
        return ent
    }
}