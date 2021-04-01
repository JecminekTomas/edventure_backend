package com.jecminek.edventure_backend.domain.subject

import com.jecminek.edventure_backend.enums.Faculty
import io.swagger.v3.oas.annotations.media.Schema

data class SubjectDto(
    @Schema(description = "ID is only for response")
    var id: Long,
    @Schema(description = "Code of subject", example = "PTN")
    var code: String,
    @Schema(description = "Subject title", example = "Programovací techniky")
    var title: String,
    @Schema(description = "Faculty, where subject being taught", example = "FACULTY_OF_BUSINESS_AND_ECONOMICS")
    var faculty: Faculty
)
