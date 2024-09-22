package com.github.smartchat.common.init

import com.github.smartchat.domaincore.domain.account.AccountAdd
import com.github.smartchat.domaincore.domain.account.AccountRole
import com.github.smartchat.domaincore.domain.account.AccountService
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomAdd
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomService
import com.github.smartchat.domaincore.domain.chatuser.ChatUserService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class InitRunnerDev(
    private val accountService: AccountService,
    private val chatRoomService: ChatRoomService,
    private val chatUserService: ChatUserService,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val ac1 = accountService.create(AccountAdd(
            role = AccountRole.MEMBER,
            username = "ac1",
            password = "ac1",
            nickname = "ac1",
        ))

        val cr1 = chatRoomService.create(ChatRoomAdd(
            title = "test1",
            createdById = ac1.id,
            isPrivate = false,
        ))
    }
}