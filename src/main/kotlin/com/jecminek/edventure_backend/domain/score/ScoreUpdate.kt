package com.jecminek.edventure_backend.domain.score

data class ScoreUpdate(
    var userVote: UserVote,
    var reviewId: Long
)