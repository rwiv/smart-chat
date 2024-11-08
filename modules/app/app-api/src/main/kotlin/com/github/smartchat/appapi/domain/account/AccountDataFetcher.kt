package com.github.smartchat.appapi.domain.account

import com.github.smartchat.commonutils.exceptions.NotFoundException
import com.github.smartchat.domaincore.domain.account.AccountAdd
import com.github.smartchat.domaincore.domain.account.AccountPublic
import com.github.smartchat.domaincore.domain.account.AccountService
import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomService
import com.github.smartchat.domaincore.domain.chatuser.ChatUser
import com.github.smartchat.domaincore.domain.chatuser.ChatUserQuery
import com.github.smartchat.domaincore.domain.chatuser.ChatUserService
import com.netflix.graphql.dgs.*
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

@DgsComponent
class AccountDataFetcher(
    private val accountService: AccountService,
    private val chatUserService: ChatUserService,
    private val chatRoomService: ChatRoomService,
    private val passwordEncoder: PasswordEncoder,
) {

    @DgsQuery
    fun accountsAll(): List<AccountPublic> {
        return accountService.findAll().map { it.toPublic() }
    }

    @DgsQuery
    fun account(@InputArgument username: String?, authentication: Authentication): AccountPublic? {
        if (username !== null) {
            return accountService.findByUsername(username)?.toPublic()
        } else {
            val account = authentication.details as AccountPublic
            return accountService.findById(account.id)?.toPublic()
        }
    }

    @DgsQuery
    fun accounts(@InputArgument id: UUID): AccountPublic? {
        return accountService.findById(id)?.toPublic()
    }

    @DgsMutation
    fun createAccount(req: AccountAdd): AccountPublic {
        req.password = passwordEncoder.encode(req.password)
        return accountService.create(req).toPublic()
    }

    @DgsData(parentType = "Account")
    fun chatUsers(dfe: DgsDataFetchingEnvironment): List<ChatUser> {
        val account = dfe.getSource<AccountPublic>() ?: throw NotFoundException("Account not found")
        val query = ChatUserQuery(account = true, chatRoom = ChatRoomQuery(createdBy = false))
        return chatUserService.findByAccountId(account.id, query)
    }

    @DgsData(parentType = "Account")
    fun chatRooms(dfe: DgsDataFetchingEnvironment): List<ChatRoom> {
        val account = dfe.getSource<AccountPublic>() ?: throw NotFoundException("Account not found")
        val query = ChatRoomQuery(createdBy = true)
        return chatRoomService.findAll(query).filter { it.createdById == account.id }
    }

    @DgsData(parentType = "Account")
    fun createdAt(dfe: DgsDataFetchingEnvironment): OffsetDateTime {
        val account = dfe.getSource<AccountPublic>() ?: throw NotFoundException("Account not found")
        return account.createdAt.atOffset(ZoneOffset.UTC)
    }
}
