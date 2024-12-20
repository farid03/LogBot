package com.vk.logbot.webjmix.config.security

import io.jmix.security.role.annotation.ResourceRole
import io.jmix.security.role.annotation.SpecificPolicy
import io.jmix.securityflowui.role.UiMinimalPolicies
import io.jmix.securityflowui.role.annotation.MenuPolicy
import io.jmix.securityflowui.role.annotation.ViewPolicy

@ResourceRole(name = "Anonymous role", code = AnonymousRole.CODE)
interface AnonymousRole : UiMinimalPolicies {

    companion object {
        const val CODE = "anonymous-role"
    }

    @ViewPolicy(viewIds = ["*"])
    @MenuPolicy(menuIds = [])
    @SpecificPolicy(resources = ["*"])
    fun anonymous()
}
