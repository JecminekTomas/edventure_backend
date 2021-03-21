package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.domain.user.UserDto
import com.jecminek.edventure_backend.domain.user.convertToEntity

data class ReviewDto(
    var id: Long,
    var stars: Double,
    var verbalEvaluation: String,
    var helpful: Int,
    var unhelpful: Int,

    /** Reviewer = Who is reviewing.
     * Reviewed = Who is being reviewed.*/
    // TODO: 19.03.2021 var userPicture
)

// FIXME: 19.03.2021 In every UPDATE will change date of Review. Not sure if it's wrong.
// FIXME: 20.03.2021 No way to send whole USER, just ID.

fun ReviewDto.convertToEntity() = Review(
    stars = stars,
    verbalEvaluation = verbalEvaluation,
    helpful = helpful,
    unhelpful = unhelpful,
    reviewTimestamp = System.currentTimeMillis() / 1000L,
    // TODO: 19.03.2021 var userPicture
)