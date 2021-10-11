package com.jecminek.edventure_backend.domain.user

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Email

data class UserRequest(

    @Schema(description = "Firstname of user", example = "Marek")
    var firstName: String,

    @Schema(description = "Lastname of user", example = "Vrána")
    var lastName: String,

    @Email(message = "This is not email")
    @Schema(description = "E-mail address of user", example = "m_vrana@email.com")
    var userName: String,

    var password: String
)

fun UserRequest.convertRequestToEntity() = User(
    firstName = firstName,
    lastName = lastName,
    userName = userName,
    password = password
)


