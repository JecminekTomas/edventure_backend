package com.jecminek.edventure_backend.domain.user.request

import com.jecminek.edventure_backend.domain.contact.Contact
import com.jecminek.edventure_backend.domain.contact.ContactRequest
import io.swagger.v3.oas.annotations.media.Schema

data class RegisterRequest(
    @Schema(description = "Firstname of user", example = "Marek")
    val firstName: String,

    @Schema(description = "Lastname of user", example = "Vrána")
    val lastName: String,

    @Schema(description = "Username of user", example = "xmarek")
    val userName: String,

    @Schema(description = "User password", example = "heslo")
    val password: String,

    @Schema(description = "List of user contacts")
    val contacts: List<ContactRequest>?
)



