package com.github.smartchat.appapi.security.handlers

import com.github.smartchat.commonutils.exceptions.HttpException
import com.github.smartchat.appapi.common.error.resolver.HttpErrorSender
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class DefaultAuthenticationEntryPoint(val sender: HttpErrorSender) : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val message = "spring security EntryPoint Exception, request uri: ${request.requestURI}"
        val ex = HttpException(HttpStatus.UNAUTHORIZED.value(), message, authException)
        sender.send(request, response, ex)
    }
}