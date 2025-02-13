package com.orca.gateway.auth.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.orca.gateway.exception.AuthError
import com.orca.gateway.exception.ErrorResponse
import com.orca.gateway.external.AuthService
import kotlinx.coroutines.reactor.mono
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class AuthFilter(
    private val authService: AuthService,
    private val objectMapper: ObjectMapper
) : GatewayFilter {

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val authToken = exchange.request.headers.getFirst("Authorization")

        return if (authToken == null || !authToken.startsWith("Bearer ")) {
            val response = ErrorResponse(AuthError.TOKEN_NOT_FOUND)
            exchange.response.run {
                statusCode = response.status
                writeWith(Mono.just(bufferFactory().wrap(objectMapper.writeValueAsBytes(response))))
            }
            Mono.empty()
        } else {
            val token = authToken.substring(7)
            mono {
                authService.verifyToken(token)
            }
                .flatMap { chain.filter(exchange) }
        }
    }
}

