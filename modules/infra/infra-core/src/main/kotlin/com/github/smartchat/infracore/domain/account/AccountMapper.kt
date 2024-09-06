package com.github.smartchat.infracore.domain.account

import com.github.smartchat.domaincore.domain.account.Account
import com.github.smartchat.domaincore.domain.account.AccountAdd
import org.springframework.stereotype.Component

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
        )
    }

    fun addToEnt(req: AccountAdd): AccountEnt {
        return AccountEnt(
            role = req.role,
            username = req.username,
            password = req.password,
            nickname = req.nickname,
            avatarUrl = req.avatarUrl,
        )
    }
}