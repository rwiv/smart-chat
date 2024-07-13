package com.github.smartchat.infracore.domain.chatroom

import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import com.github.smartchat.infracore.common.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class ChatRoomEntity(
    @Column
    val title: String
) : BaseTimeEntity() {

    companion object {
        fun fromChatRoom(chatRoom: ChatRoom) = ChatRoomEntity(
            title = chatRoom.title,
        )
    }

    fun toChatRoom() = ChatRoom(
        id = id ?: throw NullPointerException("id is null"),
        title = title,
    )
}