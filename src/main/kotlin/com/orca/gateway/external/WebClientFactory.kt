package com.orca.gateway.external

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientFactory(
    private val serverConfig: ServerConfig
) {
    private val clients = serverConfig.map.mapValues { (name, info) ->
        WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .baseUrl("https://${info.host}:${info.port}/$name")
            .build()
    }

    fun getClient(name: String): WebClient {
        return checkNotNull(clients[name]) { "Undefined service name: $name" }
    }
}


