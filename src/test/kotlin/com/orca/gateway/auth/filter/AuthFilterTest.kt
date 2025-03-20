package com.orca.gateway.auth.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.orca.gateway.auth.utils.getDefaultErrorResponse
import com.orca.gateway.external.AuthService
import io.kotest.core.spec.style.FunSpec
import io.mockk.*
import io.mockk.mockk
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

class AuthFilterTest : FunSpec({
    val authService = mockk<AuthService>()
    val mapper = mockk<ObjectMapper>()
    val filter = AuthFilter(authService, mapper)

    val exchange = mockk<ServerWebExchange>()
    val headers = mockk<HttpHeaders>()
    val chain = mockk<GatewayFilterChain>()
    val request = mockk<ServerHttpRequest>()
    val response = mockk<ServerHttpResponse>()

    beforeTest {
        every { exchange.request } returns request
        every { exchange.response } returns response
        every { request.headers } returns headers
        every { headers.getFirst("Authorization") } returns "Bearer token"
    }

    test("토큰 검증 성공") {
        every { authService.verifyToken(any()) } returns Mono.empty()
        every { chain.filter(exchange) } returns Mono.empty()

        StepVerifier.create(filter.filter(exchange, chain))
            .verifyComplete()
        verify { chain.filter(exchange) }
    }
})