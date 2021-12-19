package com.jecminek.edventure_backend.domain.user.request

import com.jecminek.edventure_backend.domain.contact.Contact
import io.swagger.v3.oas.annotations.media.Schema

data class RegisterRequest(

    @Schema(description = "Firstname of user", example = "Marek")
    var firstName: String,

    @Schema(description = "Lastname of user", example = "Vrána")
    var lastName: String,

    @Schema(description = "Username of user", example = "xmarek")
    var userName: String,

    @Schema(description = "User password", example = "heslo")
    var password: String,

    @Schema(description = "List of user contacts")
    var contacts: List<Contact>
)



