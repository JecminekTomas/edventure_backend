package com.jecminek.edventure_backend.domain.user.request

import io.swagger.v3.oas.annotations.media.Schema

data class LoginRequest(
    @Schema(description = "Username", example = "xmarek")
    var userName: String,

    @Schema(description = "User password", example = "heslo")
    var password: String,
)