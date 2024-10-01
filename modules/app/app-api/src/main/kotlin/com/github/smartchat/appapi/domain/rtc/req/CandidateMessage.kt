package com.github.smartchat.appapi.domain.rtc.req

import com.github.smartchat.appapi.domain.rtc.rtc.RTCIceCandidate

data class CandidateMessage(
    val candidate: RTCIceCandidate,
    val senderId: String,
    val receiverId: String,
)
