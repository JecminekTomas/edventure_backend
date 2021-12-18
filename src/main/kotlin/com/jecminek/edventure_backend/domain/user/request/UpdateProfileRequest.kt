package com.jecminek.edventure_backend.domain.user.request

import io.swagger.v3.oas.annotations.media.Schema

data class UpdateProfileRequest(
    @Schema(description = "Id of user", example = "1")
    var id: Long,

    @Schema(description = "Firstname of user", example = "Marek")
    var firstName: String,

    @Schema(description = "Lastname of user", example = "Vrána")
    var lastName: String,
)