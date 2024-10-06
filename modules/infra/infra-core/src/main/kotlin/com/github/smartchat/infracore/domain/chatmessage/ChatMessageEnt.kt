package com.github.smartchat.infracore.domain.chatmessage

import com.github.smartchat.infracore.common.BaseEntity
import com.github.smartchat.infracore.domain.account.AccountEnt
import com.github.smartchat.infracore.domain.chatroom.ChatRoomEnt
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "chat_message", indexes = [
//    Index(name = "idx_chat_room_id_num", columnList = "chat_room_id, num", unique = true),
//    Index(name = "idx_chat_room_id_num_desc", columnList = "chat_room_id, num DESC", unique = true),
    Index(name = "idx_chat_room_id_num_asc", columnList = "chat_room_id, num"),
    Index(name = "idx_chat_room_id_num_desc", columnList = "chat_room_id, num DESC"),
])
class ChatMessageEnt(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    val chatRoom: ChatRoomEnt,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val createdBy: AccountEnt,

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime,

    @Column(length = 200, nullable = false, updatable = false)
    val content: String,

    @Column
    val num: Int,

    id: UUID? = null,
) : BaseEntity(id) {
//    @Column(unique = true)
//    private val crIdNum: String = "${chatRoom.id}-${num}"
}
