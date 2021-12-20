package com.jecminek.edventure_backend.domain.faculty

import io.swagger.v3.oas.annotations.media.Schema

data class FacultyDTO(
    @Schema(description = "ID is used only in response")
    val id: Long,
    @Schema(description = "Faculty name", example = "Agronomická fakulta")
    val name: String,
    @Schema(description = "Faculty code", example = "AF")
    val code: String?,
    @Schema(description = "ID of university where faculty belongs.", example = "1")
    val universityId: Long,
)

