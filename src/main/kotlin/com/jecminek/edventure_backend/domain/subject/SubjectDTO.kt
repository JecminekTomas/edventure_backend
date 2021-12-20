package com.jecminek.edventure_backend.domain.subject

import io.swagger.v3.oas.annotations.media.Schema

data class SubjectDTO(
    @Schema(description = "ID is only for response")
    val id: Long,
    @Schema(description = "Code of subject", example = "PTN")
    val code: String,
    @Schema(description = "Subject title", example = "Programovací techniky")
    val name: String,
    @Schema(description = "Faculty, where subject being taught", example = "PEF")
    val facultyId: Long,
)



