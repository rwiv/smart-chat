package com.github.smartchat.infracore.domain.chatroom

import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomAdd
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import com.github.smartchat.infracore.domain.account.AccountJpaRepository
import com.github.smartchat.infracore.domain.account.AccountMapper
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class ChatRoomMapper(
    private val accountJpaRepository: AccountJpaRepository,
    private val accountMapper: AccountMapper,
) {

    fun entToDto(ent: ChatRoomEnt, query: ChatRoomQuery): ChatRoom {
        val createdBy = if (query.createdBy) {
            accountMapper.entToDto(ent.createdBy).toPublic()
        } else null

        return ChatRoom(
            id = ent.id ?: throw NullPointerException("id is null"),
            title = ent.title,
            createdById = ent.createdBy.id ?: throw NullPointerException("createdById is null"),
            createdBy = createdBy,
            createdAt = ent.createdAt ?: throw NullPointerException("createdAt is null"),
            password = ent.password,
            isPrivate = ent.isPrivate,
            userCnt = ent.userCnt,
        )
    }

    fun dtoToEnt(dto: ChatRoom, query: ChatRoomQuery): ChatRoomEnt {
        val createdBy = if (query.createdBy) {
            accountJpaRepository.findById(dto.createdById).getOrNull()
                ?: throw NullPointerException("createdBy is null")
        } else null

        return ChatRoomEnt(
            title = dto.title,
            createdBy = createdBy ?: throw NullPointerException("createdById is null"),
            password = dto.password,
            isPrivate = dto.isPrivate,
            userCnt = dto.userCnt,
        )
    }

    fun addToEnt(req: ChatRoomAdd): ChatRoomEnt {
        val createdBy = accountJpaRepository.findById(req.createdById).getOrNull()
            ?: throw NullPointerException("createdBy is null")

        return ChatRoomEnt(
            title = req.title,
            createdBy = createdBy,
            password = req.password,
            isPrivate = req.isPrivate,
            userCnt = 0,
        )
    }
}