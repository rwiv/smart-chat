package com.github.smartchat.infracore.domain.chatroom

import com.github.smartchat.infracore.common.BaseEntity
import com.github.smartchat.infracore.domain.account.AccountEnt
import com.github.smartchat.infracore.domain.chatuser.ChatUserEnt
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
class ChatRoomEnt(
    @Column
    val title: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id")
    val createdBy: AccountEnt,

    @Column
    val password: String?,

    @Column
    val isPrivate: Boolean,

    @Column
    var userCnt: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shared_chat_user_id")
    var sharedChatUser: ChatUserEnt? = null,

    @Column
    val createdAt: LocalDateTime,

    id: UUID? = null,
) : BaseEntity(id) {

    companion object {
        fun onlyId(id: UUID): ChatRoomEnt {
            return ChatRoomEnt(
                id = id,
                title = "",
                createdBy = AccountEnt.onlyId(id),
                password = "",
                isPrivate = false,
                userCnt = 0,
                createdAt = LocalDateTime.now(),
            )
        }
    }
}