package com.github.smartchat.common.webrtc

data class DescriptionMessage(
    val description: RTCSessionDescription,
    val senderId: Long,
    val receiverId: Long,
)
