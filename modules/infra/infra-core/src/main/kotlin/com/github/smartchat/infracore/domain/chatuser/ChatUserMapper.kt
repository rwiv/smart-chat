package com.github.smartchat.infracore.domain.chatuser

import com.github.smartchat.domaincore.domain.chatuser.ChatUser
import com.github.smartchat.domaincore.domain.chatuser.ChatUserAdd
import com.github.smartchat.domaincore.domain.chatuser.ChatUserQuery
import com.github.smartchat.infracore.domain.account.AccountJpaRepository
import com.github.smartchat.infracore.domain.account.AccountMapper
import com.github.smartchat.infracore.domain.chatroom.ChatRoomJpaRepository
import com.github.smartchat.infracore.domain.chatroom.ChatRoomMapper
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class ChatUserMapper(
    private val accountJpaRepository: AccountJpaRepository,
    private val chatRoomJpaRepository: ChatRoomJpaRepository,
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
        )
    }

    fun addToEnt(req: ChatUserAdd): ChatUserEnt {
        val account = accountJpaRepository.findById(req.accountId).getOrNull()
            ?: throw NullPointerException("account is null")
        val chatRoom = chatRoomJpaRepository.findById(req.chatRoomId).getOrNull()
            ?: throw NullPointerException("chatRoom is null")

        return ChatUserEnt(
            account= account,
            chatRoom= chatRoom,
        )
    }
}