package com.github.smartchat.appapi.domain.rtc.req

import com.github.smartchat.appapi.domain.rtc.rtc.RTCSessionDescription

data class DescriptionMessage(
    val description: RTCSessionDescription,
    val senderId: String,
    val receiverId: String,
)
