package com.jecminek.edventure_backend.domain.user.request

import io.swagger.v3.oas.annotations.media.Schema

data class LoginRequest(
    @Schema(description = "Username", example = "xmarek")
    val userName: String,

    @Schema(description = "User password", example = "Heslo123.")
    val password: String,
)