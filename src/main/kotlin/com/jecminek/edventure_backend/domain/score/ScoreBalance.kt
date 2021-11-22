package com.jecminek.edventure_backend.domain.score

data class ScoreBalance (
    var helpful: Long,
    var unhelpful: Long,
    var userVoted: ScoreDTO?
)