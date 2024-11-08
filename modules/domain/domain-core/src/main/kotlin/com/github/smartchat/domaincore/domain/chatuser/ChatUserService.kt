package com.github.smartchat.domaincore.domain.chatuser

import com.github.smartchat.commonutils.exceptions.HttpException
import com.github.smartchat.commonutils.exceptions.NotFoundException
import com.github.smartchat.domaincore.domain.account.AccountRepository
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomQuery
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatUserService(
    private val chatUserRepository: ChatUserRepository,
    private val chatRoomRepository: ChatRoomRepository,
    private val accountRepository: AccountRepository,
) {

    @Transactional
    fun create(req: ChatUserAddDomain, query: ChatUserQuery): ChatUser {
        val chatRoom = chatRoomRepository.findById(req.chatRoomId, ChatRoomQuery(createdBy = false))
            ?: throw NotFoundException("ChatRoom not found")

        // check if the ChatRoom is password protected
        if (chatRoom.password != req.password) {
            throw HttpException(403, "Password is incorrect")
        }

        val chatUsers = chatUserRepository.findByChatRoom(chatRoom.id, query)
        val searched = chatUsers.find { it.accountId == req.accountId }
        if (searched != null) {
            return searched
        }

        // add ChatUser
        val chatUser = chatUserRepository.add(ChatUserAddInfra(
            accountId = req.accountId,
            chatRoomId = req.chatRoomId,
        ), query)

        // increase user count
        chatRoomRepository.updateUserCnt(req.chatRoomId, true, ChatRoomQuery(createdBy = false))

        return chatUser
    }

    @Transactional
    fun delete(id: UUID, requesterId: UUID, query: ChatUserQuery): ChatUser {
        val chatUser = chatUserRepository.findById(id, query)
            ?: throw NotFoundException("ChatUser not found")
        if (chatUser.accountId != requesterId) {
            throw HttpException(403, "Requester is not the owner of the chat user")
        }
        chatUserRepository.delete(id, query)
        return chatUser
    }

    fun findAll(query: ChatUserQuery) : List<ChatUser> {
        return chatUserRepository.findAll(query)
    }

    fun findById(id: UUID, query: ChatUserQuery): ChatUser? {
        return chatUserRepository.findById(id, query)
    }

    fun findByChatRoomId(chatRoomId: UUID, query: ChatUserQuery): List<ChatUser> {
        return chatUserRepository.findByChatRoom(chatRoomId, query)
    }

    fun findByAccountId(accountId: UUID, query: ChatUserQuery): List<ChatUser> {
        val account = accountRepository.findById(accountId) ?: throw NotFoundException("Account not found")
        return chatUserRepository.findByAccount(account, query)
    }
}