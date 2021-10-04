package com.jecminek.edventure_backend.domain.user

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Email

data class UserResponse(
    @Schema(description = "Id of user", example = "1")
    var id: Long,

    @Schema(description = "Firstname of user", example = "Marek")
    var firstName: String,

    @Schema(description = "Lastname of user", example = "Vrána")
    var lastName: String,

    @Email(message = "This is not email")
    @Schema(description = "E-mail address of user", example = "m_lee@email.com")
    var email: String,
)


