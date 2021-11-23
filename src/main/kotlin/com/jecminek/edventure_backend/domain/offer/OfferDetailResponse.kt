package com.jecminek.edventure_backend.domain.offer

import com.jecminek.edventure_backend.domain.review.ReviewResponse
import io.swagger.v3.oas.annotations.media.Schema

data class OfferDetailResponse(
    @Schema(description = "Offer detail", example = "{22, 3.2}")
    var offer: OfferResponse,
    @Schema(description = "List of Reviews", example = "{22, 3.2}")
    var reviews: List<ReviewResponse>,
)