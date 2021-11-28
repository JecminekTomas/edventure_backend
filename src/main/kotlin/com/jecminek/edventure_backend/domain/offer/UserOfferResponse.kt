package com.jecminek.edventure_backend.domain.offer

import com.jecminek.edventure_backend.domain.review.ReviewBalance
import io.swagger.v3.oas.annotations.media.Schema

data class UserOfferResponse(
    @Schema(description = "Id of offer", example = "1")
    var id: Long,
    @Schema(description = "Offer price", example = "300")
    var price: Double,
    @Schema(description = "Note for lecture", example = "Take a bag")
    var note: String?,
    @Schema(description = "Balance of reviews", example = "{22, 3.2}")
    var reviewBalance: ReviewBalance,
    @Schema(description = "Id of subject", example = "22")
    var subjectId: Long,
    @Schema(description = "Name of subject", example = "Programovací techniky")
    var subjectName: String,
    @Schema(description = "Code of subject", example = "PTN")
    var subjectCode: String,
)