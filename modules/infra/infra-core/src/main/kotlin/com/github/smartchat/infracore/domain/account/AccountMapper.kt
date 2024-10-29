package com.github.smartchat.infracore.domain.account

import com.github.smartchat.domaincore.domain.account.Account
import com.github.smartchat.domaincore.domain.account.AccountAdd
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AccountMapper {

    fun entToDto(ent: AccountEnt): Account {
        return Account(
            id = ent.id ?: throw NullPointerException("id is null"),
            role = ent.role,
            username = ent.username,
            password = ent.password,
            nickname = ent.nickname,
            avatarUrl = ent.avatarUrl,
            createdAt = ent.createdAt,
        )
    }

    fun dtoToEnt(dto: Account): AccountEnt {
        return AccountEnt(
            id = dto.id,
            role = dto.role,
            username = dto.username,
            password = dto.password,
            nickname = dto.nickname,
            avatarUrl = dto.avatarUrl,
            createdAt = dto.createdAt,
        )
    }

    fun addToEnt(req: AccountAdd): AccountEnt {
        return AccountEnt(
            role = req.role,
            username = req.username,
            password = req.password,
            nickname = req.nickname,
            avatarUrl = req.avatarUrl,
            createdAt = LocalDateTime.now(),
        )
    }
}