package com.jecminek.edventure_backend.domain.university

import io.swagger.v3.oas.annotations.media.Schema

data class UniversityDTO(
    @Schema(description = "ID is only for response")
    var id: Long,
    @Schema(description = "University name", example = "Mendelova univerzita")
    var name: String
)

fun UniversityDTO.convertToEntity() = University(
    name = name
)

