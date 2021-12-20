package com.jecminek.edventure_backend.domain.score

data class ScoreBalance (
    val helpfulCount: Long,
    val unhelpfulCount: Long,
    val userVote: UserVote?
)