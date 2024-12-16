package com.vk.logbot.server.repositories

import com.vk.logbot.server.models.Config
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ConfigRepository : JpaRepository<Config, Long> {

    fun findConfigsByNameAndUserId(name: String, userId: Long): List<Config>

    fun findConfigsByUserId(userId: Long): List<Config>

    fun findConfigById(id: Long): Config?

    fun findConfigsByActive(active: Boolean): List<Config>
}