package com.jecminek.edventure_backend.domain.score

data class ScoreUpdate(
    val userVote: UserVote,
    val reviewId: Long
)