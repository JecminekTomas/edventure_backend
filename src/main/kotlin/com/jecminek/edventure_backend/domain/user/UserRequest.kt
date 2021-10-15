package com.jecminek.edventure_backend.domain.user

import io.swagger.v3.oas.annotations.media.Schema

data class UserRequest(

    @Schema(description = "Firstname of user", example = "Marek")
    var firstName: String,

    @Schema(description = "Lastname of user", example = "Vrána")
    var lastName: String,

    @Schema(description = "E-mail address of user", example = "xmarek")
    var userName: String,

    @Schema(description = "User password", example = "heslo")
    var password: String,
)

fun UserRequest.convertRequestToEntity() = User(
    firstName = firstName,
    lastName = lastName,
    userName = userName,
    password = password
)


