package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.domain.user.UserIdDto
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class ReviewDto(
    @Schema(description = "ID is only for response", hidden = true)
    var id: Long,

    @Min(1, message = "Minimum is 1 star") @Max(5, message = "Maximum is 5 stars")
    @Schema(description = "Rating in stars", example = "3")
    var stars: Double,

    @Size(min = 10, max = 500)
    @Schema(description = "Verbal evaulation from student to teacher or vice versa", example = "The lesson was OK. Only no added materials are included in EXPENSIVE lecture.")
    var verbalEvaluation: String,
    @Min(0, message = "Helpful cannot be negative, use unhelpful parameter instead")
    @Schema(description = "Helpfulness of review", example = "10")
    var helpful: Int,

    @Min(0, message = "Unhelpful cannot be negative, use unhelpful parameter instead")
    @Schema(description = "Unhelpfulness of review", example = "2")
    var unhelpful: Int,


    // FIXME: 30.03.2021 Schemas
    var reviewerId: Long,

    var reviewedId: Long

    //FIXME: 28.03.2021 I must send in update reviewer and reviewed!
    //FIXME: 28.03.2021 In create there is no way to send helpful and unhelpful.
    // FIXME: 28.03.2021 Reviewer must be student/ teacher and Reviewed must be teacher/student

    // TODO: 19.03.2021 var userPicture
)

// FIXME: 19.03.2021 In every UPDATE will change date of Review. Not sure if it's wrong.
// FIXME: 20.03.2021 No way to send whole USER, just ID.

