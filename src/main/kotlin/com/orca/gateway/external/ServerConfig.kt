package com.orca.gateway.external

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "external")
data class ServerConfig(
    val map: Map<String, ServerInfo> = emptyMap()
) {
    data class ServerInfo(
        val host: String,
        val port: Int
    )
}