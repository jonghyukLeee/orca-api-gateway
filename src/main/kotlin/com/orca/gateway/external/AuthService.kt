package com.orca.gateway.external

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class AuthService(
    private val webClient: WebClient
) {

    private val baseUrl = "http://orca-auth:8010/auth"
    suspend fun verifyToken(token: String) {
        webClient.get()
            .uri {
                it.path("${baseUrl}/token")
                    .queryParam("token", token)
                    .build()
            }.retrieve()
            .toBodilessEntity()
            .subscribe()
    }
}