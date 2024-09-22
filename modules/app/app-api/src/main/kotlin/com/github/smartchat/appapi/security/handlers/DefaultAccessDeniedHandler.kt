package com.github.smartchat.appapi.security.handlers

import com.github.smartchat.commonutils.exceptions.HttpException
import com.github.smartchat.appapi.common.error.resolver.HttpErrorSender
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import org.springframework.security.access.AccessDeniedException

@Component
class DefaultAccessDeniedHandler(val sender: HttpErrorSender) : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        val ex = HttpException(HttpStatus.FORBIDDEN.value(), accessDeniedException.message, accessDeniedException)
        sender.send(request, response, ex)
    }
}