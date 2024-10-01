package com.github.smartchat.appapi.common.init

import com.github.smartchat.domaincore.domain.account.AccountAdd
import com.github.smartchat.domaincore.domain.account.AccountRole
import com.github.smartchat.domaincore.domain.account.AccountService
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomAdd
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomService
import com.github.smartchat.domaincore.domain.chatuser.ChatUserAddDomain
import com.github.smartchat.domaincore.domain.chatuser.ChatUserQuery
import com.github.smartchat.domaincore.domain.chatuser.ChatUserService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class InitRunnerDev(
    private val accountService: AccountService,
    private val chatRoomService: ChatRoomService,
    private val chatUserService: ChatUserService,
    private val passwordEncoder: PasswordEncoder,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val admin = accountService.create(AccountAdd(
            role = AccountRole.ADMIN,
            username = "admin",
            password = passwordEncoder.encode("admin"),
            nickname = "admin",
        ))
        val ac1 = accountService.create(AccountAdd(
            role = AccountRole.MEMBER,
            username = "user1@gmail.com",
            password = passwordEncoder.encode("1234"),
            nickname = "user1",
        ))
        val ac2 = accountService.create(AccountAdd(
            role = AccountRole.MEMBER,
            username = "user2@gmail.com",
            password = passwordEncoder.encode("1234"),
            nickname = "user2",
        ))
        val ac3 = accountService.create(AccountAdd(
            role = AccountRole.MEMBER,
            username = "user3@gmail.com",
            password = passwordEncoder.encode("1234"),
            nickname = "user3",
        ))

        val cr1 = chatRoomService.create(ChatRoomAdd(
            title = "test room 1",
            createdById = ac1.id,
            isPrivate = false,
        ))
        val cr2 = chatRoomService.create(ChatRoomAdd(
            title = "test room 2",
            createdById = admin.id,
            isPrivate = false,
        ))

        chatUserService.create(ChatUserAddDomain(
            accountId = ac2.id,
            chatRoomId = cr1.id,
            password = null,
        ), ChatUserQuery(false, null))
//        chatUserService.create(ChatUserAddDomain(
//            accountId = ac3.id,
//            chatRoomId = cr1.id,
//            password = null,
//        ), ChatUserQuery(false, null))
    }
}