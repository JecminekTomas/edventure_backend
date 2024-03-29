package com.jecminek.edventure_backend.constant

object SecurityConstants {
    const val SECRET = "SECRET_KEY"
    const val EXPIRATION_TIME: Long = 900000 // 15 mins
    const val TOKEN_PREFIX = "Bearer "
    const val HEADER_STRING = "Authorization"
    const val SIGN_UP_URL = "/users"
}