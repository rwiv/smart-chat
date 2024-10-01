package com.github.smartchat.appapi.domain

import com.github.smartchat.appapi.helpers.ac
import com.github.smartchat.appapi.helpers.cr
import com.github.smartchat.domaincore.domain.account.AccountService
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomService
import com.github.smartchat.domaincore.domain.chatuser.ChatUserQuery
import com.github.smartchat.domaincore.domain.chatuser.ChatUserService
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ChatUserServiceTest(
    @Autowired private val accountService: AccountService,
    @Autowired private val chatRoomService: ChatRoomService,
    @Autowired private val chatUserService: ChatUserService,
) {

    @Transactional
    @Test fun `test find by account`() {
        val a1 = accountService.create(ac("user1"))

        val cr1 = chatRoomService.create(cr(a1.id, "cr1"))
        val cr2 = chatRoomService.create(cr(a1.id, "cr2"))

        val query = ChatUserQuery(account = true, chatRoom = ChatRoomQuery(createdBy = false))
        val chatUsers = chatUserService.findByAccountId(a1.id, query)
        chatUsers.forEach { println(it.id) }
    }

    @Transactional
    @Test fun `test update sharedChatUser`() {
        val a1 = accountService.create(ac("user1"))
        val cr1 = chatRoomService.create(cr(a1.id, "cr1"))
        println(cr1.sharedChatUserId)

        val chatUsers = chatUserService.findByChatRoomId(cr1.id, ChatUserQuery(account = true, chatRoom = null))

        val ret = chatRoomService.updateSharedChatUser(cr1.id, chatUsers[0].id, ChatRoomQuery(createdBy = false))
        println(ret.sharedChatUserId)
    }
}