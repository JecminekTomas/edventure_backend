package com.jecminek.edventure_backend.domain.review

import io.swagger.v3.oas.annotations.media.Schema

data class ReviewRequest(
    @Schema(description = "Rating in stars", example = "3")
    val stars: Double,

    @Schema(
        description = "Verbal evaluation",
        example = "The lesson was OK. Only no added materials are included in EXPENSIVE lecture."
    )
    val verbalEvaluation: String,

    @Schema(description = "Does reviewer want to stay anonymous", example = "false")
    val anonymous: Boolean,

    @Schema(description = "OfferId which being reviewed", example = "1")
    val offerId: Long

)

