package com.jecminek.edventure_backend.domain.faculty

import com.jecminek.edventure_backend.base.BaseEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "FACULTY")
class Faculty(
    var name: String = "",
    var code: String? = ""
) : BaseEntity()

fun Faculty.convertToDTO() = FacultyDTO(
    id = id,
    name = name,
    code = code
)