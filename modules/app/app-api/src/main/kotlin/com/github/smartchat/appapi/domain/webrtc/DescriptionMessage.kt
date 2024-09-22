package com.github.smartchat.appapi.domain.webrtc

data class DescriptionMessage(
    val description: RTCSessionDescription,
    val senderId: Long,
    val receiverId: Long,
)
