package com.github.smartchat.infracore.domain.chatroom

import com.github.smartchat.infracore.common.BaseTimeEntity
import com.github.smartchat.infracore.domain.account.AccountEnt
import jakarta.persistence.*
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

    id: UUID? = null,
) : BaseTimeEntity(id) {

    companion object {
        fun onlyId(id: UUID): ChatRoomEnt {
            return ChatRoomEnt(
                id = id,
                title = "",
                createdBy = AccountEnt.onlyId(id),
                password = "",
                isPrivate = false,
                userCnt = 0,
            )
        }
    }
}