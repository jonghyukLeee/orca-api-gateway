package com.orca.gateway.config.route

import com.orca.gateway.auth.filter.AuthFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig(
    private val properties: RouteProperties,
    private val authFilter: AuthFilter
) {
    @Bean
    fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {
        val routes = builder.routes()
        properties.services.forEach { service ->
            routes.route {
                if (service.authRequired) {
                    it.filters { filter(authFilter) }
                }
                it.path(service.path)
                it.uri(service.uri)
            }
        }
        return routes.build()
    }

}