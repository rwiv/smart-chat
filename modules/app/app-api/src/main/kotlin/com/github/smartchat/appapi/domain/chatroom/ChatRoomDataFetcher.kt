package com.github.smartchat.appapi.domain.chatroom

import com.github.smartchat.commonutils.exceptions.NotFoundException
import com.github.smartchat.domaincore.domain.account.AccountPublic
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessage
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessageQuery
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessageService
import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomAdd
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomService
import com.github.smartchat.domaincore.domain.chatuser.ChatUser
import com.github.smartchat.domaincore.domain.chatuser.ChatUserQuery
import com.github.smartchat.domaincore.domain.chatuser.ChatUserService
import com.netflix.graphql.dgs.*
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.security.core.Authentication
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

@DgsComponent
class ChatRoomDataFetcher(
    private val chatRoomService: ChatRoomService,
    private val chatUserService: ChatUserService,
    private val chatMessageService: ChatMessageService,
    private val template: SimpMessagingTemplate,
) {

    @DgsQuery
    fun chatRoom(@InputArgument id: UUID): ChatRoom? {
        val query = ChatRoomQuery(createdBy = true)
        return chatRoomService.findById(id, query)
    }

    @DgsQuery
    fun chatRoomsAll(): List<ChatRoom> {
        val query = ChatRoomQuery(createdBy = true)
        return chatRoomService.findAll(query)
    }

    @DgsQuery
    fun chatRooms(@InputArgument page: Int, @InputArgument size: Int): List<ChatRoom> {
        val query = ChatRoomQuery(createdBy = true)
        return chatRoomService.findByPage(page, size, query)
    }

    @DgsMutation
    fun createChatRoom(req: ChatRoomAddReq, authentication: Authentication): ChatRoom {
        val account = authentication.details as AccountPublic
        val query = ChatRoomQuery(createdBy = true)
        return chatRoomService.create(ChatRoomAdd(
            title = req.title,
            createdById = account.id,
            isPrivate = false,
            password = req.password,
        ), query)
    }

    @DgsMutation
    fun deleteChatRoom(@InputArgument chatRoomId: UUID, authentication: Authentication): ChatRoom {
        val account = authentication.details as AccountPublic
        val query = ChatRoomQuery(createdBy = true)
        return chatRoomService.delete(chatRoomId, account.id, query)
    }

    @DgsMutation
    fun updateSharedChatUser(
        @InputArgument chatRoomId: UUID,
        @InputArgument isClose: Boolean,
        authentication: Authentication,
    ): ChatRoom {
        val account = authentication.details as AccountPublic
        return chatRoomService.updateSharedChatUser(chatRoomId, account.id, isClose, ChatRoomQuery(createdBy = true)).also {
            template.convertAndSend("/sub/chat-rooms/${chatRoomId}/shared", it)
        }
    }

    @DgsData(parentType = "ChatRoom")
    fun createdAt(dfe: DgsDataFetchingEnvironment): OffsetDateTime {
        val chatRoom = dfe.getSource<ChatRoom>() ?: throw NotFoundException("Chatroom not found")
        return chatRoom.createdAt.atOffset(ZoneOffset.UTC)
    }

    @DgsData(parentType = "ChatRoom")
    fun createdBy(dfe: DgsDataFetchingEnvironment): AccountPublic {
        val chatRoom = dfe.getSource<ChatRoom>() ?: throw NotFoundException("ChatRoom not found")
        return chatRoom.createdBy ?: throw NotFoundException("Account not found")
    }

    @DgsData(parentType = "ChatRoom")
    fun chatMessages(dfe: DgsDataFetchingEnvironment): List<ChatMessage> {
        val chatRoom = dfe.getSource<ChatRoom>() ?: throw NotFoundException("ChatRoom not found")
        return chatMessageService.findByChatRoomId(chatRoom.id, ChatMessageQuery(true, null))
    }

    @DgsData(parentType = "ChatRoom")
    fun chatUsers(dfe: DgsDataFetchingEnvironment): List<ChatUser> {
        val chatRoom = dfe.getSource<ChatRoom>() ?: throw NotFoundException("ChatRoom not found")
        return chatUserService.findByChatRoomId(chatRoom.id, ChatUserQuery(true, null))
    }

    // TODO: implement
    @DgsData(parentType = "ChatRoom")
    fun hasPassword(dfe: DgsDataFetchingEnvironment): Boolean {
        return false
    }
}