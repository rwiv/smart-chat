package com.github.smartchat.domain.webrtc

data class DescriptionMessage(
    val description: RTCSessionDescription,
    val senderId: Long,
    val receiverId: Long,
)
