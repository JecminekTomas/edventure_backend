package com.jecminek.edventure_backend.domain.user.request

import io.swagger.v3.oas.annotations.media.Schema

data class UpdateProfileRequest(
    @Schema(description = "Id of user", example = "1")
    val id: Long,

    @Schema(description = "Firstname of user", example = "Marek")
    val firstName: String,

    @Schema(description = "Lastname of user", example = "Vrána")
    val lastName: String,
)