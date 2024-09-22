package com.github.smartchat.appapi.common.error.data

import com.github.smartchat.commonutils.exceptions.HttpException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

class HttpError(
    val uuid: String,
    val timestamp: LocalDateTime,
    val exception: HttpException,
    req: HttpServletRequest,
) {
    val status: Int = exception.status
    val message: String = exception.userMessage ?: exception.message ?: ""
    val headers: Map<String, String>? = exception.headers

    val path: String = req.requestURI
    val params: Map<String, Array<String>> = req.parameterMap

    val errors: Any? = exception.errors
    val stackTrace: String = exception.stackTraceToString()

    fun toResponse() = HttpErrorResponse(
        status = status,
        message = message,
        uuid = uuid,
        timestamp = timestamp.toString(),
        exception = exception.javaClass.simpleName
    )

    fun toResponseEntity(): ResponseEntity<HttpErrorResponse> {
        return ResponseEntity
            .status(status)
            .headers { headers?.forEach { header -> it[header.key] = header.value } }
            .body(toResponse())
    }
}