package com.orca.gateway.exception

import com.orca.gateway.util.getCurrentTimestamp

class ErrorResponse(
    val serviceName: String,
    val code: String,
    val message: String,
    val timestamp: String
) {
    constructor(e: AuthError) : this(
        serviceName = "gateway",
        code = e.name,
        message = e.message,
        timestamp = getCurrentTimestamp(),
    )
}