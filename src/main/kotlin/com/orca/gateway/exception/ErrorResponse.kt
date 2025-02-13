package com.orca.gateway.exception

import org.springframework.http.HttpStatus

class ErrorResponse(
    val status: HttpStatus,
    val message: String
) {
    constructor(e: AuthError): this(
        status = e.httpStatus!!,
        message = e.message
    )
    companion object {
        fun default(): ErrorResponse {
            return ErrorResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR,
                message = "UNDEFINED EXCEPTION",
            )
        }
    }
}