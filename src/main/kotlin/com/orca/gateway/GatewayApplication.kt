package com.orca.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class GatewayApplication

suspend fun main(args: Array<String>) {
	runApplication<GatewayApplication>(*args)
}