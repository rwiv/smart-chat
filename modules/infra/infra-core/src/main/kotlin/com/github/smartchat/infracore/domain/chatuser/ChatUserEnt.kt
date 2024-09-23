package com.github.smartchat.infracore.domain.chatuser

import com.github.smartchat.infracore.common.BaseTimeEntity
import com.github.smartchat.infracore.domain.account.AccountEnt
import com.github.smartchat.infracore.domain.chatroom.ChatRoomEnt
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
class ChatUserEnt(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    val account: AccountEnt,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    val chatRoom: ChatRoomEnt,

    id: UUID? = null,
) : BaseTimeEntity(id) {

    companion object {
        fun onlyId(id: UUID): ChatUserEnt {
            return ChatUserEnt(
                id = id,
                account = AccountEnt.onlyId(id),
                chatRoom = ChatRoomEnt.onlyId(id),
            )
        }
    }
}