package com.github.smartchat.appapi.helpers

import com.github.smartchat.domaincore.domain.account.AccountAdd
import com.github.smartchat.domaincore.domain.account.AccountRole

fun ac(username: String) = AccountAdd(
    role = AccountRole.MEMBER,
    username = username,
    password = "1234",
    nickname = "${username}!!",
)
