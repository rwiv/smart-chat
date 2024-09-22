package com.github.smartchat.appapi.domain.chatroom

import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatRoomController(
    private val chatRoomService: ChatRoomService,
) {

    @GetMapping("/chat-rooms")
    fun findByAll(): List<ChatRoom> {
        return chatRoomService.findAll()
    }
}