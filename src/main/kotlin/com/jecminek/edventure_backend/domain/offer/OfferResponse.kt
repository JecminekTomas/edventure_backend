package com.jecminek.edventure_backend.domain.offer

import com.jecminek.edventure_backend.domain.review.ReviewBalance
import io.swagger.v3.oas.annotations.media.Schema

data class OfferResponse(
    @Schema(description = "Id of offer", example = "1")
    var id: Long,
    @Schema(description = "Offer price", example = "300")
    var price: Double,
    @Schema(description = "Note for lecture", example = "Take a bag")
    var note: String?,
    @Schema(description = "Teacher's firstname", example = "Zdeněk")
    var teacherFirstName: String,
    @Schema(description = "Teacher's lastname", example = "Novák")
    var teacherLastName: String,
    @Schema(description = "Balance of reviews", example = "{22, 3.2}")
    var reviewBalance: ReviewBalance
)