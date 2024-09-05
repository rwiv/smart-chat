package com.github.smartchat.common.webrtc

data class CandidateMessage(
    val candidate: RTCIceCandidate,
    val senderId: Long,
    val receiverId: Long,
)
