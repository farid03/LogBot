package com.vk.logbot.webjmix.entity

import io.jmix.core.HasTimeZone
import io.jmix.core.annotation.Secret
import io.jmix.core.entity.annotation.JmixGeneratedValue
import io.jmix.core.entity.annotation.SystemLevel
import io.jmix.core.metamodel.annotation.JmixEntity
import io.jmix.security.authentication.JmixUserDetails
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import org.springframework.security.core.GrantedAuthority
import java.util.*

@Suppress("JmixInstanceName")
@JmixEntity
@Entity
@Table(name = "USER_")
open class User : JmixUserDetails, HasTimeZone {

    @Id
    @Column(name = "ID", nullable = false)
    @JmixGeneratedValue
    var id: UUID? = null

    @Version
    @Column(name = "VERSION", nullable = false)
    var version: Int? = null

    @Column(name = "USERNAME", nullable = false)
    @get:JvmName("getUsername_")
    var username: String? = null

    @Secret
    @SystemLevel
    @Column(name = "PASSWORD")
    @get:JvmName("getPassword_")
    var password: String? = null

    @Column(name = "FIRST_NAME")
    var firstName: String? = null

    @Column(name = "LAST_NAME")
    var lastName: String? = null

    @Email
    @Column(name = "EMAIL")
    var email: String? = null

    @Column(name = "ACTIVE")
    var active: Boolean? = true

    @Column(name = "TIME_ZONE_ID")
    @get:JvmName("getTimeZoneId_")
    var timeZoneId: String? = null

    @Transient
    private var userAuthorities: Collection<GrantedAuthority?>? = null

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return username
    }

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return userAuthorities ?: emptyList()
    }

    override fun setAuthorities(authorities: Collection<GrantedAuthority?>) {
        this.userAuthorities = authorities
    }

    override fun getTimeZoneId(): String? {
        return timeZoneId
    }

    override fun isAutoTimeZone(): Boolean {
        return true
    }
}