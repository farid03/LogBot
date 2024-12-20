package com.vk.logbot.webjmix.config.security

import com.vk.logbot.webjmix.entity.User
import io.jmix.securitydata.user.AbstractDatabaseUserRepository
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Primary
@Component("UserRepository")
class DatabaseUserRepository : AbstractDatabaseUserRepository<User>() {

    override fun getUserClass(): Class<User> = User::class.java

    override fun initAnonymousUser(anonymousUser: User) {
        val authorities = grantedAuthoritiesBuilder
            .addResourceRole(AnonymousRole.CODE)
            .build()
        anonymousUser.authorities = authorities
    }
}