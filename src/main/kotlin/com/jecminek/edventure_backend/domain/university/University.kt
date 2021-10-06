package com.jecminek.edventure_backend.domain.university

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.faculty.Faculty
import javax.persistence.*

@Entity
@Table(name = "UNIVERSITY")
class University(
    var name: String = "",
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "university", cascade = [CascadeType.ALL])
    var faculties: MutableList<Faculty> = mutableListOf(),
) : BaseEntity()

fun University.convertToDTO() = UniversityDTO(
    id = id,
    name = name
)