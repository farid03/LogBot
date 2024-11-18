package com.vk.logbot.serverrestclient

import org.springframework.web.client.RestClient

class MockClient(
    private val serverUrl: String,
    private val restClient: RestClient = RestClient.create()
) {
    fun get(): Any {
        return restClient.get()
            .uri {
                it.path("$serverUrl/mocks")
                    .queryParam("param1", "value1")
                    .build()
            }
            .retrieve()
            .toEntity(Any::class.java)
    }

    fun post(): Any {
        return restClient.post().uri("$serverUrl/mocks").body(Any()).retrieve().toEntity(Any::class.java)
    }
}