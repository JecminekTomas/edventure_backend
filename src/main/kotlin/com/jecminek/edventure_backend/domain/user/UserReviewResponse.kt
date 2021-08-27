package com.jecminek.edventure_backend.domain.user

import io.swagger.v3.oas.annotations.media.Schema

data class UserReviewResponse (
    @Schema(description = "Id of user", example = "1")
    var id: Long,

    @Schema(description = "Firstname of user", example = "Muhammad")
    var firstName: String,

    @Schema(description = "Lastname of user", example = "Lee")
    var lastName: String,
)