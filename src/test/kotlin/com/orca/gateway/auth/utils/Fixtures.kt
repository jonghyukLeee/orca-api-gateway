package com.orca.gateway.auth.utils

import com.orca.gateway.exception.ErrorResponse

fun getDefaultErrorResponse(): ErrorResponse {
    return ErrorResponse(
        serviceName = "gateway",
        code = "code",
        message = "message",
        timestamp = "timestamp"
    )
}