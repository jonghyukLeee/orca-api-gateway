package com.orca.gateway.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getCurrentTimestamp(): String {
    return LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}