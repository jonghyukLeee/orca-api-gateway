package com.orca.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GatewayApplication

suspend fun main(args: Array<String>) {
	runApplication<GatewayApplication>(*args)
}
