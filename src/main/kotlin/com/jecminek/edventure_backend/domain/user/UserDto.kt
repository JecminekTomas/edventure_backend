package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.enums.UserRole
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class UserDto(

    @Schema(description = "ID is only for response", hidden = true)
    var id: Long,

    @Schema(description = "Firstname of user", example = "Muhammad")
    var firstName: String,

    @Schema(description = "Lastname of user", example = "Lee")
    var lastName: String,

    @Email
    @Schema(description = "E-mail address of user", example = "m_lee@email.com")
    var email: String,

    @Size(min = 10, max = 500)
    @Schema(
        description = "Biography of User",
        example = "Hello, my name is Muhammad and I'm student of archeology. " +
                "Make a reservation on my lecture in Monday, Tuesday or Sunday"
    )
    var biography: String?,

    @Pattern(regexp = "^(\"+420)? ?[1-9][0-9]{2} ?[0-9]{3} ?[0-9]{3}$")
    @Schema(description = " of User", example = "12345678")
    var phoneNumber: String?,

    @Schema(description = "Is the User student or teacher or both?", example =  "[\"TEACHER\"]")
    var roles: MutableList<UserRole>
)

fun UserDto.convertToEntity() = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    biography = biography,
    phoneNumber = phoneNumber,
    roles = roles
)