package com.jecminek.edventure_backend.domain.subject

import io.swagger.v3.oas.annotations.media.Schema

data class SubjectDTO(
    @Schema(description = "ID is only for response")
    var id: Long,
    @Schema(description = "Code of subject", example = "PTN")
    var code: String,
    @Schema(description = "Subject title", example = "Programovací techniky")
    var name: String,
    @Schema(description = "Faculty, where subject being taught", example = "PEF")
    var facultyId: Long,
)



