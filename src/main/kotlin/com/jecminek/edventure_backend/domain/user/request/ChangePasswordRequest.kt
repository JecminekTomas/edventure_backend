package com.jecminek.edventure_backend.domain.user.request

import io.swagger.v3.oas.annotations.media.Schema

data class ChangePasswordRequest(
    @Schema(description = "Username of user", example = "xmarek")
    val userName: String,

    @Schema(description = "Old user password", example = "heslo")
    val oldPassword: String,

    @Schema(description = "New user password", example = "heslo1")
    val newPassword: String,
)