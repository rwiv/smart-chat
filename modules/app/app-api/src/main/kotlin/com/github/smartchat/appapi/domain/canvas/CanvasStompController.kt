package com.github.smartchat.appapi.domain.canvas

import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

@Controller
class CanvasWsController(
    private val template: SimpMessagingTemplate,
) {

    @MessageMapping("/chat-rooms/{chatRoomId}/canvas")
    fun chatMessageHandle(
        message: String,
//        authentication: Principal,
        @DestinationVariable chatRoomId: String,
    ) {
        template.convertAndSend("/sub/chat-rooms/${chatRoomId}/canvas", message)
    }
}