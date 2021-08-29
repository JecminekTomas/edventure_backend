package com.jecminek.edventure_backend.domain.subject

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.enums.Faculty
import com.jecminek.edventure_backend.enums.University
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table
import javax.validation.constraints.NotEmpty


@Entity
@Table(name = "\"SUBJECT\"")
class Subject(
    var code: String = "",
    var title: String = "",

    @Enumerated(EnumType.STRING)
    @NotEmpty
    var faculty: Faculty = Faculty.FACULTY,

    @Enumerated(EnumType.STRING)
    @NotEmpty
    var university: University = University.UNIVERSITY,

) : BaseEntity()

fun Subject.convertToDTO() = SubjectDTO(
    id = id,
    code = code,
    title = title,
    faculty = faculty,
    university = university,
)



