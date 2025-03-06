package com.orca.gateway.auth.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.orca.gateway.exception.AuthError
import com.orca.gateway.exception.ErrorResponse
import com.orca.gateway.external.AuthService
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.server.reactive.ServerHttpResponse
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

        return if (isValidToken(authToken)) {
            val token = authToken!!.substring(7)
            authService.verifyToken(token)
                .flatMap {
                    exchange.response.writeResponse(
                        exchange = exchange,
                        statusCode = it.statusCode,
                        response = it.body!!
                    )
                }
                .switchIfEmpty(chain.filter(exchange))
        } else {
            exchange.response.writeResponse(
                exchange = exchange,
                statusCode = HttpStatus.UNAUTHORIZED,
                response = ErrorResponse(AuthError.TOKEN_NOT_FOUND)
            )
        }
    }

    fun ServerHttpResponse.writeResponse(exchange: ServerWebExchange, statusCode: HttpStatusCode, response: Any): Mono<Void> {
        this.statusCode = statusCode
        return writeWith(Mono.just(bufferFactory().wrap(objectMapper.writeValueAsBytes(response))))
    }

    fun isValidToken(token: String?): Boolean {
        return token != null && token.startsWith("Bearer ")
    }
}

