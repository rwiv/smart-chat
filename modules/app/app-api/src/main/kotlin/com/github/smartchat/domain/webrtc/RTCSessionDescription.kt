package com.github.smartchat.domain.webrtc

data class RTCSessionDescription(
    val type: String,
    val sdp: String,
) {

//    enum class DescriptionType {
//        OFFER,
//        ANSWER,
//    }

//    fun getTypeEnum(): DescriptionType {
//        return when(type) {
//            "offer" -> DescriptionType.OFFER
//            "answer" -> DescriptionType.ANSWER
//            else -> throw RuntimeException("not supported description type")
//        }
//    }
}
