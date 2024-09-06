package com.github.smartchat.infracore.domain.chatuser

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChatUserJpaRepository : JpaRepository<ChatUserEnt, UUID> {
}