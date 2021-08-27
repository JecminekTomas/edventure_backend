package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.domain.subject.Subject
import com.jecminek.edventure_backend.enums.UserRole
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class UserRequest(

    @Schema(description = "Firstname of user", example = "Muhammad")
    var firstName: String,

    @Schema(description = "Lastname of user", example = "Lee")
    var lastName: String,

    @Email(message = "This is not email")
    @Schema(description = "E-mail address of user", example = "m_lee@email.com")
    var email: String,
)

fun UserRequest.convertRequestToEntity() = User(
    firstName = firstName,
    lastName = lastName,
    email = email,
)


