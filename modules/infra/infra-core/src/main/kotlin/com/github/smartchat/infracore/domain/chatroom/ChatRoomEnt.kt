package com.github.smartchat.infracore.domain.chatroom

import com.github.smartchat.infracore.common.BaseTimeEntity
import com.github.smartchat.infracore.domain.account.AccountEnt
import jakarta.persistence.*

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
) : BaseTimeEntity()