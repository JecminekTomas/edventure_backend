package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.domain.score.ScoreBalance
import com.jecminek.edventure_backend.domain.subject.SubjectDTO
import com.jecminek.edventure_backend.domain.user.UserResponse
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
        description = "Verbal evaluation from student to teacher or vice versa",
        example = "The offer was OK. Only no added materials are included in EXPENSIVE lecture."
    )
    var verbalEvaluation: String,

    @Schema(description = "Time, when review was made.")
    var reviewTimestamp: Long,

    @Schema(description = "User, who wrote review")
    var userFrom: UserResponse?,

    @Schema(description = "User, who review is for")
    var userTo: UserResponse,

    @Schema(description = "Scores of review")
    var score: ScoreBalance,

    @Schema(description = "Subject which was taught")
    var subject: SubjectDTO


)

