package com.orca.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class GatewayApplication

suspend fun main(args: Array<String>) {
	runApplication<GatewayApplication>(*args)
}