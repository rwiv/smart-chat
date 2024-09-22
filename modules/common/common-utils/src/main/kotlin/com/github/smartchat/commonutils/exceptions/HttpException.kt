package com.github.smartchat.commonutils.exceptions

open class HttpException(
    val status: Int,
    message: String? = null,
    cause: Throwable? = null,
    val errors: Any? = null,
    val headers: Map<String, String>? = null,
    val userMessage: String? = message,
) : RuntimeException(message, cause)
