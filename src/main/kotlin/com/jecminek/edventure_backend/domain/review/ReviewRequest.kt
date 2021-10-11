package com.jecminek.edventure_backend.domain.review

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class ReviewRequest(
    @Min(1, message = "Minimum is 1 star") @Max(5, message = "Maximum is 5 stars")
    @Schema(description = "Rating in stars", example = "3")
    var stars: Double,

    @Size(min = 10, max = 500)
    @Schema(
        description = "Verbal evaluation from student to teacher or vice versa",
        example = "The offer was OK. Only no added materials are included in EXPENSIVE lecture."
    )
    var verbalEvaluation: String,

    @Schema(description = "Does reviewer want to stay anonymous", example = "false")
    var anonymous: Boolean,

    @Schema(description = "ID of user, who is in position of reviewer in review", example = "2")
    var reviewerId: Long,

    @Schema(description = "ID of user, who is in position of reviewed in review", example = "1")
    var reviewedId: Long

)
