package com.github.smartchat.appapi.security.filters

import com.github.smartchat.appapi.security.authentication.AuthenticationToken

data class LoginRequest(
    val username: String,
    val password: String,
) {
    companion object {
        fun of(requestedAuth: AuthenticationToken) = LoginRequest(
            username = requestedAuth.principal,
            password = requestedAuth.credentials!!
        )
    }
}
