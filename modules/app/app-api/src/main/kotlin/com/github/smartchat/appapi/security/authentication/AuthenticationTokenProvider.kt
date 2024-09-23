package com.github.smartchat.appapi.security.authentication

import com.github.smartchat.appapi.security.userdetails.AccountDetails
import com.github.smartchat.appapi.security.userdetails.AccountDetailsService
import com.github.smartchat.commonutils.exceptions.HttpException
import com.github.smartchat.commonutils.exceptions.NotFoundException
import com.github.smartchat.domaincore.domain.account.AccountService
import jakarta.transaction.Transactional
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class AuthenticationTokenProvider(
    val accountService: AccountService,
    val accountDetailsService: AccountDetailsService,
    val passwordEncoder: PasswordEncoder,
) : AuthenticationProvider {

    @Transactional
    override fun authenticate(authentication: Authentication): Authentication {
        val username = (authentication as AuthenticationToken).principal
        val password = authentication.credentials
        val accountDetails: AccountDetails = accountDetailsService.loadUserByUsername(username)

        if (!passwordEncoder.matches(password, accountDetails.password)) {
            throw HttpException(401, "Invalid password")
        }

        val account = accountService.findById(accountDetails.id)?.toPublic()
            ?: throw NotFoundException("not found account")

        return AuthenticationToken.successToken(
            accountDetails.username,
            accountDetails.authorities,
            account,
        )
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == AuthenticationToken::class.java
    }
}