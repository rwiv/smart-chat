package com.github.smartchat.infracore.domain.chatuser

import com.github.smartchat.domaincore.domain.chatuser.ChatUser
import com.github.smartchat.domaincore.domain.chatuser.ChatUserAddInfra
import com.github.smartchat.domaincore.domain.chatuser.ChatUserQuery
import com.github.smartchat.infracore.domain.account.AccountEnt
import com.github.smartchat.infracore.domain.account.AccountMapper
import com.github.smartchat.infracore.domain.chatroom.ChatRoomEnt
import com.github.smartchat.infracore.domain.chatroom.ChatRoomMapper
import org.springframework.stereotype.Component

@Component
class ChatUserMapper(
    private val accountMapper: AccountMapper,
    private val chatRoomMapper: ChatRoomMapper,
) {

    fun entToDto(ent: ChatUserEnt, query: ChatUserQuery): ChatUser {
        val account = if (query.account) {
            accountMapper.entToDto(ent.account).toPublic()
        } else null
        val chatRoom = if (query.chatRoom != null) {
            chatRoomMapper.entToDto(ent.chatRoom, query.chatRoom!!)
        } else null

        return ChatUser(
            id = ent.id ?: throw NullPointerException("id is null"),
            accountId = ent.account.id ?: throw NullPointerException("accountId is null"),
            account = account,
            chatRoomId = ent.chatRoom.id ?: throw NullPointerException("chatRoomId is null"),
            chatRoom = chatRoom,
            createdAt = ent.createdAt ?: throw NullPointerException("createdAt is null"),
        )
    }

    fun addToEnt(req: ChatUserAddInfra): ChatUserEnt {
        return ChatUserEnt(
            account= AccountEnt.onlyId(req.accountId),
            chatRoom= ChatRoomEnt.onlyId(req.chatRoomId),
        )
    }
}