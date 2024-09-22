package com.github.smartchat.commonutils.exceptions

class NotFoundException(
    message: String,
   cause: Throwable? = null,
) : HttpException(404, message, cause)
