package com.orca.gateway.exception

import org.springframework.http.HttpStatus

enum class GatewayError(val status: HttpStatus? = HttpStatus.INTERNAL_SERVER_ERROR, val message: String) {
    UNDEFINED_EXCEPTION(message = "Sorry, undefined exception"),
}