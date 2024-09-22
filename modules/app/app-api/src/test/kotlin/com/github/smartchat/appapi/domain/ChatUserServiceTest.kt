package com.github.smartchat.appapi.domain

import com.github.smartchat.appapi.helpers.ac
import com.github.smartchat.appapi.helpers.cr
import com.github.smartchat.domaincore.domain.account.AccountService
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomService
import com.github.smartchat.domaincore.domain.chatuser.ChatUserQuery
import com.github.smartchat.domaincore.domain.chatuser.ChatUserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class ChatUserServiceTest(
    @Autowired private val accountService: AccountService,
    @Autowired private val chatRoomService: ChatRoomService,
    @Autowired private val chatUserService: ChatUserService,
) {

    @Transactional
    @Test
    fun `test find by account`() {
        val a1 = accountService.create(ac("user1"))

        val cr1 = chatRoomService.create(cr(a1.id, "cr1"))
        val cr2 = chatRoomService.create(cr(a1.id, "cr2"))

        val query = ChatUserQuery(account = true, chatRoom = ChatRoomQuery(createdBy = false))
        val chatUsers = chatUserService.findByAccountId(a1.id, query)
        chatUsers.forEach { println(it.account?.username) }
    }
}