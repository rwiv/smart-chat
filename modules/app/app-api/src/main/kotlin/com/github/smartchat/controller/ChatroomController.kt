package com.github.smartchat.controller

import com.github.smartchat.domaincore.domain.chatroom.ChatRoom
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatroomController(
    private val chatRoomRepository: ChatRoomRepository
) {

    @GetMapping("/chat-rooms")
    fun findByAll(): List<ChatRoom> {
        return chatRoomRepository.findAll()
    }
}