package com.jecminek.edventure_backend.domain.score

import io.swagger.v3.oas.annotations.media.Schema

data class ScoreDTO(
    @Schema(description = "ID is only for response")
    val id: Long,
    @Schema(description = "If true, helpful, if false unhelpful", example = "true")
    val helpful: Boolean,
    @Schema(description = "Id of review, which getting score.", example = "2")
    val reviewId: Long,
)