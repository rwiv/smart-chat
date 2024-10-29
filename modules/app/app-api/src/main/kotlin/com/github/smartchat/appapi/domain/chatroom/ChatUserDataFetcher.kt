package com.github.smartchat.appapi.domain.chatroom

import com.github.smartchat.commonutils.exceptions.NotFoundException
import com.github.smartchat.domaincore.domain.account.AccountPublic
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessage
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessageQuery
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessageService
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import com.github.smartchat.domaincore.domain.chatuser.ChatUser
import com.github.smartchat.domaincore.domain.chatuser.ChatUserAddDomain
import com.github.smartchat.domaincore.domain.chatuser.ChatUserQuery
import com.github.smartchat.domaincore.domain.chatuser.ChatUserService
import com.netflix.graphql.dgs.*
import org.springframework.security.core.Authentication
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

@DgsComponent
class ChatUserDataFetcher(
    private val chatUserService: ChatUserService,
    private val chatMessageService: ChatMessageService,
) {

    @DgsQuery
    fun chatUsersAll(): List<ChatUser> {
        val query = ChatUserQuery(account = true, chatRoom = ChatRoomQuery(createdBy = false))
        return chatUserService.findAll(query)
    }

    @DgsQuery
    fun chatUser(@InputArgument id: UUID): ChatUser? {
        val query = ChatUserQuery(account = true, chatRoom = ChatRoomQuery(createdBy = false))
        return chatUserService.findById(id, query)
    }

    @DgsMutation
    fun createChatUser(
        @InputArgument chatRoomId: UUID,
        @InputArgument password: String?,
        authentication: Authentication,
    ): ChatUser {
        val account = authentication.details as AccountPublic
        val req = ChatUserAddDomain(chatRoomId, account.id, password)
        val query = ChatUserQuery(account = true, chatRoom = ChatRoomQuery(createdBy = false))
        return chatUserService.create(req, query)
    }

    @DgsMutation
    fun deleteChatUserMe(
        @InputArgument chatRoomId: UUID,
        authentication: Authentication,
    ): ChatUser {
        val account = authentication.details as AccountPublic
        val query = ChatUserQuery(account = true, chatRoom = ChatRoomQuery(createdBy = false))
        return chatUserService.delete(chatRoomId, account.id, query)
    }

    @DgsData(parentType = "ChatUser")
    fun createdAt(dfe: DgsDataFetchingEnvironment): OffsetDateTime {
        val chatUser = dfe.getSource<ChatUser>() ?: throw NotFoundException("ChatUser not found")
        return chatUser.createdAt.atOffset(ZoneOffset.UTC)
    }

    @DgsData(parentType = "ChatUser")
    fun chatMessages(dfe: DgsDataFetchingEnvironment): List<ChatMessage> {
        val chatUser = dfe.getSource<ChatUser>() ?: throw NotFoundException("ChatUser not found")
        val query = ChatMessageQuery(createdBy = false, chatRoom = null)
        return chatMessageService.findAll(query).filter { it.createdById == chatUser.accountId }
    }
}
