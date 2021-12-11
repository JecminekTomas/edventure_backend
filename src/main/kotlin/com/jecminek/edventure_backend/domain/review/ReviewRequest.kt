package com.jecminek.edventure_backend.domain.review

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class ReviewRequest(
    @Schema(description = "Rating in stars", example = "3")
    var stars: Double,

    @Schema(
        description = "Verbal evaluation",
        example = "The lesson was OK. Only no added materials are included in EXPENSIVE lecture."
    )
    var verbalEvaluation: String,

    @Schema(description = "Does reviewer want to stay anonymous", example = "false")
    var anonymous: Boolean,

    @Schema(description = "OfferId which being reviewed", example = "1")
    var offerId: Long

)

