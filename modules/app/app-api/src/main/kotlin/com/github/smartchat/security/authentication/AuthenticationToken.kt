package com.github.smartchat.security.authentication

import com.github.smartchat.domaincore.domain.account.AccountPublic
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class AuthenticationToken(
    private val principal: String,
    private val credentials: String?,
    authorities: Collection<GrantedAuthority>?,
) : AbstractAuthenticationToken(authorities) {

    companion object {

        fun requestToken(
            principal: String,
            credentials: String?,
            authorities: Collection<GrantedAuthority>? = null,
        ): AuthenticationToken {
            val requestToken = AuthenticationToken(principal, credentials, authorities)
            requestToken.setRequest()
            return requestToken
        }

        fun successToken(
            principal: String,
            authorities: Collection<GrantedAuthority>?,
            account: AccountPublic,
        ): AuthenticationToken {
            val successToken = AuthenticationToken(principal, null, authorities)
            successToken.setSuccess()
            successToken.details = account
            return successToken
        }
    }

    private fun setRequest() {
        super.setAuthenticated(false)
    }

    private fun setSuccess() {
        super.setAuthenticated(true)
    }

    override fun getPrincipal(): String = principal
    override fun getCredentials(): String? = credentials
}