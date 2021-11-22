package com.jecminek.edventure_backend.domain.score

data class ScoreBalance (
    var helpfulCount: Long,
    var unhelpfulCount: Long,
    var userVote: UserVote?
)