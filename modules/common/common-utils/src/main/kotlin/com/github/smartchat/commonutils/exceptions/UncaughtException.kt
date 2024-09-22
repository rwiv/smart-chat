package com.github.smartchat.commonutils.exceptions

class UncaughtException(
    status: Int,
    cause: MustCatchException,
    message: String? = "uncaught ${cause.javaClass.simpleName}",
) : HttpException(status, message, cause)
