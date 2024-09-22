package com.github.smartchat.appapi.security.filters

import com.github.smartchat.appapi.security.authentication.AuthenticationToken
import com.github.smartchat.domaincore.domain.account.AccountRole
import com.github.smartchat.domaincore.domain.account.AccountService
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.filter.GenericFilterBean

//@Profile("dev")
//@Component
class DevAuthFilter(
    private val accountService: AccountService,
) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val matcher = AntPathRequestMatcher("/**")
        val match = matcher.matcher(request as HttpServletRequest)
        if (!match.isMatch) {
            return chain.doFilter(request, response)
        }

        val securityContext = SecurityContextHolder.getContext()
        if (securityContext?.authentication?.isAuthenticated == true) {
            return chain.doFilter(request, response)
        }

        val reqApiKey: String = request.getHeader("Authorization") ?: ""
        if (reqApiKey == "admin") {
//        if (reqApiKey == "admin" || request.servletPath.startsWith("/graphql")) {
            val account = accountService.findAll().first { it.role == AccountRole.ADMIN }
            val roles = ArrayList<GrantedAuthority>().apply {
                add(SimpleGrantedAuthority(account.role.name))
            }
            val successToken = AuthenticationToken.successToken(
                account.username, roles, account.toPublic(),
            )
            securityContext.authentication = successToken
        }

        chain.doFilter(request, response)
    }
}
