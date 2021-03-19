package com.jecminek.edventure_backend.domain.review

data class ReviewDto(
    var stars: Int,
    var verbalEvaluation: String,
    var helpful: Int,
    var unhelpful: Int,
    var reviewTimestamp: Long
    // TODO: 18.03.2021 var user: UserDTO?
)

fun ReviewDto.convertToEntity() = Review(
    stars = stars,
    verbalEvaluation = verbalEvaluation,
    helpful = helpful,
    unhelpful = unhelpful,
    reviewTimestamp = reviewTimestamp
)