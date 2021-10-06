package com.jecminek.edventure_backend.domain.faculty

import io.swagger.v3.oas.annotations.media.Schema

data class FacultyDTO(
    @Schema(description = "ID is used only in response")
    var id: Long,
    @Schema(description = "Faculty name", example = "Agronomická fakulta")
    var name: String,
    @Schema(description = "Faculty code", example = "AF")
    var code: String?,
)

fun FacultyDTO.convertToEntity() = Faculty(
    name = name,
    code = code
)