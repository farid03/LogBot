package com.vk.logbot.server.repositories

import com.vk.logbot.server.models.LogEntry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.ZonedDateTime

@Repository
interface LogEntryRepository: JpaRepository<LogEntry, Long> {

    fun deleteByReceivedAtLessThanEqual(date: ZonedDateTime): Long
    fun findByReceivedAtLessThanEqual(date: ZonedDateTime): List<LogEntry>

    @Query("select max(le.receivedAt) from LogEntry le")
    fun getMaxReceivedAt(): ZonedDateTime?
}