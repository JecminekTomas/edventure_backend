package com.jecminek.edventure_backend.domain.contact

import com.jecminek.edventure_backend.enums.ContactType
import io.swagger.v3.oas.annotations.media.Schema

data class ContactResponse(
    @Schema(description = "ID is only for response")
    val id: Long,

    @Schema(description = "Type of contact", example = "FACEBOOK")
    val contactType: ContactType,

    @Schema(description = "Value of contact", example = "Tomáš Novotný")
    val value: String
)