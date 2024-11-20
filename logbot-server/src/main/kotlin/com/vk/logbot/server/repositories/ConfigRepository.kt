package com.vk.logbot.server.repositories

import com.vk.logbot.commons.dto.EditConfigDto
import com.vk.logbot.server.models.Config
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ConfigRepository : JpaRepository<Config, Long> {

    fun findConfigsByNameAndUserId(name: String, userId: Long): List<Config>

    fun findConfigsByUserId(userId: Long): List<Config>

    @Modifying
    @Query("UPDATE Config c SET c.name = :#{#editConfig.name}, c.regExp = :#{#editConfig.regExp} WHERE c.id = :id")
    fun updateConfigById(
        @Param("editConfig") editConfig: EditConfigDto,
        @Param("id") id: Long,
    ): Int

    fun findConfigById(id: Long): Config?
}