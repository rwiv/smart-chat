package com.github.smartchat.appapi.domain.canvas

data class CanvasMessageReq(
    val type: String,
    val x: Int,
    val y: Int,
    val size: Int,
    val color: String,
)
