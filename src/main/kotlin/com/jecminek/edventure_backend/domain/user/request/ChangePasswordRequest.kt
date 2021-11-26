package com.jecminek.edventure_backend.domain.user.request

import io.swagger.v3.oas.annotations.media.Schema

data class ChangePasswordRequest(
    @Schema(description = "Username of user", example = "xmarek")
    var userName: String,

    @Schema(description = "Old user password", example = "heslo")
    var oldPassword: String,

    @Schema(description = "New user password", example = "heslo1")
    var newPassword: String,
)