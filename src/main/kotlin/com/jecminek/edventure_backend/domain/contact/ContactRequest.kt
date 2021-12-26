package com.jecminek.edventure_backend.domain.contact

import io.swagger.v3.oas.annotations.media.Schema

data class ContactRequest(

    @Schema(description = "Type of contact", example = "Facebook")
    val contactType: String,

    @Schema(description = "Value of contact", example = "Tomáš Novotný")
    val value: String,
)