package com.orca.gateway.exception

import org.springframework.http.HttpStatus

enum class AuthError(val httpStatus: HttpStatus? = HttpStatus.UNAUTHORIZED, val message: String) {
    TOKEN_NOT_FOUND(httpStatus = HttpStatus.UNAUTHORIZED, message = "Auth token not found.")
    ;
}