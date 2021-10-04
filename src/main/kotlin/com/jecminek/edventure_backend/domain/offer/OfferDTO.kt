package com.jecminek.edventure_backend.domain.offer

import io.swagger.v3.oas.annotations.media.Schema

data class OfferDTO(
    @Schema(description = "ID is used only in response")
    var id: Long,
    @Schema(description = "Offer price", example = "300")
    var price: Double,
    @Schema(description = "Is lecture online?", example = "false")
    var online: Boolean,
    @Schema(description = "Note for lecture", example = "Take a bag")
    var note: String,
)

fun OfferDTO.convertToEntity() = Offer(
    price = price,
    online = online,
    note = note
)

