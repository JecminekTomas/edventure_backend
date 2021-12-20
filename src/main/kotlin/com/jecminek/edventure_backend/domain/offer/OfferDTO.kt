package com.jecminek.edventure_backend.domain.offer

import io.swagger.v3.oas.annotations.media.Schema

data class OfferDTO(
    @Schema(description = "ID is used only in response")
    val id: Long,
    @Schema(description = "Offer price", example = "300")
    val price: Double,
    @Schema(description = "Is lecture online?", example = "false")
    val online: Boolean,
    @Schema(description = "Note for lecture", example = "Take a bag")
    val note: String?,
    @Schema(description = "ID of subject", example = "1")
    val subjectId: Long,
)



