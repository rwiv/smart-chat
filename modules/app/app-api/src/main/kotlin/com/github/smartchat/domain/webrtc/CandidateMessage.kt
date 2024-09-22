package com.github.smartchat.domain.webrtc

data class CandidateMessage(
    val candidate: RTCIceCandidate,
    val senderId: Long,
    val receiverId: Long,
)
