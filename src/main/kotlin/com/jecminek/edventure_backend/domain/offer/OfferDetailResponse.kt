package com.jecminek.edventure_backend.domain.offer

import com.jecminek.edventure_backend.domain.contact.ContactResponse
import com.jecminek.edventure_backend.domain.review.ReviewResponse
import io.swagger.v3.oas.annotations.media.Schema

data class OfferDetailResponse(
    @Schema(description = "Offer detail")
    val offer: OfferResponse,
    @Schema(description = "List of Reviews")
    val reviews: List<ReviewResponse>,
    @Schema(description = "List of contacts")
    val contacts: List<ContactResponse>,
)