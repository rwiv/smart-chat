package com.github.smartchat.misc.dev

import com.github.smartchat.domaincore.domain.chatroom.ChatRoomRepository
import com.github.smartchat.domaincore.domain.chatroom.ChatRoomAdd
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class InitRunner(
    private val chatRoomRepository: ChatRoomRepository,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        chatRoomRepository.add(ChatRoomAdd("test1"))
    }
}