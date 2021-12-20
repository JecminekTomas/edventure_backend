package com.jecminek.edventure_backend.domain.offer

import com.jecminek.edventure_backend.domain.review.ReviewBalance
import io.swagger.v3.oas.annotations.media.Schema

data class OfferResponse(
    @Schema(description = "Id of offer", example = "1")
    val id: Long,
    @Schema(description = "Offer price", example = "300")
    val price: Double,
    @Schema(description = "Note for lecture", example = "Take a bag")
    val note: String?,
    @Schema(description = "Teacher's firstname", example = "Zdeněk")
    val teacherFirstName: String,
    @Schema(description = "Teacher's lastname", example = "Novák")
    val teacherLastName: String,
    @Schema(description = "Balance of reviews", example = "{22, 3.2}")
    val reviewBalance: ReviewBalance,
    @Schema(description = "Id of subject", example = "22")
    val subjectId: Long,
    @Schema(description = "Name of subject", example = "Programovací techniky")
    val subjectName: String,
    @Schema(description = "Code of subject", example = "PTN")
    val subjectCode: String,
)