package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.domain.user.UserReviewResponse
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class ReviewResponse(
    @Schema(description = "ID of review")
    var id: Long,

    @Min(1, message = "Minimum is 1 star")
    @Max(5, message = "Maximum is 5 stars")
    @Schema(description = "Rating in stars", example = "3")
    var stars: Double,

    @Size(min = 10, max = 500)
    @Schema(
        description = "Verbal evaulation from student to teacher or vice versa",
        example = "The lesson was OK. Only no added materials are included in EXPENSIVE lecture."
    )
    var verbalEvaluation: String,

    @Schema(description = "Time, when review was made.")
    var reviewTimestamp: Long,

    @Schema(description = "ID of user, who is in position of reviewer in review")
    var reviewer: UserReviewResponse?,

    @Schema(description = "ID of user, who is in position of reviewed in review")
    var reviewed: UserReviewResponse

)

