package com.jecminek.edventure_backend.domain.faculty

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.subject.Subject
import com.jecminek.edventure_backend.domain.university.University
import javax.persistence.*

@Entity
@Table(name = "FACULTY")
class Faculty(
    var name: String = "",
    var code: String? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty", cascade = [CascadeType.ALL])
    var subjects: MutableList<Subject> = mutableListOf(),

    @ManyToOne(fetch = FetchType.EAGER)
    var university: University = University()
) : BaseEntity()


fun Faculty.convertToDTO() = FacultyDTO(
    id = id,
    name = name,
    code = code,
    universityId = university.id
)
