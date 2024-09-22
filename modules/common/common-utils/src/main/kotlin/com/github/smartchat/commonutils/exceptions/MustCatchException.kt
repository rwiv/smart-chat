package com.github.smartchat.commonutils.exceptions

open class MustCatchException(
    message: String? = null,
    cause: Throwable? = null,
) : RuntimeException(message, cause)
