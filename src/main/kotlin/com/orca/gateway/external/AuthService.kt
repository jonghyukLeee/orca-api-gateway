package com.orca.gateway.external

import com.orca.gateway.exception.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthService(
    clientFactory: WebClientFactory
) {
    val client = clientFactory.getClient("auth")
    fun verifyToken(token: String): Mono<ResponseEntity<ErrorResponse?>> {
        return client.get()
            .uri("/token")
            .exchangeToMono { response ->
                if (response.statusCode().is2xxSuccessful) {
                    Mono.empty()
                } else {
                    response.toEntity(ErrorResponse::class.java)
                }
            }
    }
}