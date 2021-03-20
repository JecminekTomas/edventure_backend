package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.enums.UserRole

data class UserDto(
    var id: Long,
    var firstName: String,
    var lastName: String,
    var email: String,
    var biography: String,
    var phoneNumber: String,
    var roles: MutableList<UserRole>
)

fun UserDto.convertToEntity() = User(
    firstName = firstName,
    lastName = lastName,
    email = email,
    biography = biography,
    phoneNumber = phoneNumber,
    roles = roles
)