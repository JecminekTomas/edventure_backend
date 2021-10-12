package com.jecminek.edventure_backend.domain.user

import io.swagger.v3.oas.annotations.media.Schema

data class UserRequestLogin(
    @Schema(description = "E-mail address of user", example = "m_vrana@email.com")
    var userName: String,

    @Schema(description = "User password", example = "heslo")
    var password: String,
)