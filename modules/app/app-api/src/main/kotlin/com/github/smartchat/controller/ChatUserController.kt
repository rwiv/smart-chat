package com.github.smartchat.controller

import com.github.smartchat.domaincore.domain.chatuser.ChatUser
import com.github.smartchat.domaincore.domain.chatuser.ChatUserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatUserController(
    private val chatUserService: ChatUserService,
) {

    @GetMapping("/chat-users")
    fun findByAll(): List<ChatUser> {
        return chatUserService.findAll()
    }
}