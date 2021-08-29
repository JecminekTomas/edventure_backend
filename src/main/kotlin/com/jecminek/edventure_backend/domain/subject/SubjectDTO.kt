package com.jecminek.edventure_backend.domain.subject

import com.jecminek.edventure_backend.enums.Faculty
import com.jecminek.edventure_backend.enums.University
import io.swagger.v3.oas.annotations.media.Schema

data class SubjectDTO(
    @Schema(description = "ID is only for response")
    var id: Long,
    @Schema(description = "Code of subject", example = "PTN")
    var code: String,
    @Schema(description = "Subject title", example = "Programovací techniky")
    var title: String,
    @Schema(description = "Faculty, where subject being taught", example = "PEF")
    var faculty: Faculty,
    @Schema(description = "University, the faculty belongs to", example = "MENDELU")
    var university: University,
)

fun SubjectDTO.convertToEntity() = Subject(
    code = code,
    title = title,
    faculty = faculty,
    university = university,
)

