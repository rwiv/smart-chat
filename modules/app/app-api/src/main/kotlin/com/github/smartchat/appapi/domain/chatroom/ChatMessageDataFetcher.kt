package com.github.smartchat.appapi.domain.chatroom

import com.github.smartchat.commonutils.exceptions.NotFoundException
import com.github.smartchat.domaincore.domain.account.AccountPublic
import com.github.smartchat.domaincore.domain.account.AccountService
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessage
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessageAdd
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessageQuery
import com.github.smartchat.domaincore.domain.chatmessage.ChatMessageService
import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import com.netflix.graphql.dgs.*
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.security.core.Authentication
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

@DgsComponent
class ChatMessageDataFetcher(
    private val chatMessageService: ChatMessageService,
    private val accountService: AccountService,
    private val template: SimpMessagingTemplate,
) {

    @DgsQuery
    fun chatMessagesAll(): List<ChatMessage> {
        val query = ChatMessageQuery(createdBy = true, chatRoom = ChatRoomQuery(createdBy = false))
        return chatMessageService.findAll(query)
    }

    @DgsQuery
    fun chatMessages(
        @InputArgument chatRoomId: UUID,
        @InputArgument page: Int,
        @InputArgument size: Int,
        @InputArgument offset: Int,
    ): List<ChatMessage> {
        val query = ChatMessageQuery(createdBy = true, chatRoom = ChatRoomQuery(createdBy = false))
        return chatMessageService.findByPage(chatRoomId, page, size, offset, query)
    }

    @DgsQuery
    fun chatMessage(@InputArgument id: UUID): ChatMessage? {
        val query = ChatMessageQuery(createdBy = true, chatRoom = ChatRoomQuery(createdBy = false))
        return chatMessageService.findById(id, query)
    }

    @DgsMutation
    fun createChatMessage(req: ChatMessageAddReq, authentication: Authentication): ChatMessage {
        val account = authentication.details as AccountPublic
        val query = ChatMessageQuery(createdBy = true, chatRoom = ChatRoomQuery(createdBy = false))
        return chatMessageService.create(ChatMessageAdd(
            chatRoomId = req.chatRoomId,
            createdById = account.id,
            content = req.content,
        ), query).also {
            template.convertAndSend("/sub/chat-rooms/${it.chatRoomId}/messages", it)
        }
    }

    @DgsData(parentType = "ChatMessage")
    fun createdBy(dfe: DgsDataFetchingEnvironment): AccountPublic {
        val chatMessage = dfe.getSource<ChatMessage>() ?: throw NotFoundException("ChatRoom not found")
        return try {
            chatMessage.createdBy ?: throw NotFoundException("Account not found")
        } catch (e: Exception) {
            accountService.findById(chatMessage.createdById)?.toPublic()
                ?: throw NotFoundException("Account not found")
        }
    }

    @DgsData(parentType = "ChatMessage")
    fun createdAt(dfe: DgsDataFetchingEnvironment): OffsetDateTime {
        val chatMessage = dfe.getSource<ChatMessage>() ?: throw NotFoundException("Chatroom not found")
        return chatMessage.createdAt.atOffset(ZoneOffset.UTC)
    }

    @DgsData(parentType = "ChatMessage")
    fun chatRoom(dfe: DgsDataFetchingEnvironment): ChatRoom {
        val chatMessage = dfe.getSource<ChatMessage>() ?: throw NotFoundException("ChatRoom not found")
        return chatMessage.chatRoom ?: throw NotFoundException("ChatRoom not found")
    }
}