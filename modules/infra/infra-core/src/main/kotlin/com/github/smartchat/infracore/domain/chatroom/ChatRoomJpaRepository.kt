package com.github.smartchat.infracore.domain.chatroom

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChatRoomJpaRepository : JpaRepository<ChatRoomEnt, UUID> {
}