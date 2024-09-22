package com.github.smartchat.appapi.domain.webrtc

data class CandidateMessage(
    val candidate: RTCIceCandidate,
    val senderId: Long,
    val receiverId: Long,
)
