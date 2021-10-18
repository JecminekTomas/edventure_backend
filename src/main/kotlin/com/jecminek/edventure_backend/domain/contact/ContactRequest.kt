package com.jecminek.edventure_backend.domain.contact

import com.jecminek.edventure_backend.enums.ContactType
import io.swagger.v3.oas.annotations.media.Schema

data class ContactRequest(
    @Schema(description = "ID is only for response")
    var id: Long,

    @Schema(description = "Type of contact", example = "FACEBOOK")
    var contactType: ContactType,

    @Schema(description = "Value of contact", example = "Tomáš Novotný")
    var value: String,
)