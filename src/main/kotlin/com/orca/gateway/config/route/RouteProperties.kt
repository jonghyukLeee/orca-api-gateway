package com.orca.gateway.config.route

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "routes")
class RouteProperties(
    val services: List<Service>
) {
    data class Service(
        val id: String,
        val uri: String,
        val path: String,
        val authRequired: Boolean,
    )
}