package com.jecminek.edventure_backend.domain.subject

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.faculty.Faculty
import com.jecminek.edventure_backend.domain.offer.Offer
import javax.persistence.*


@Entity
@Table(name = "\"SUBJECT\"")
class Subject(
    var code: String = "",
    var name: String = "",

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject", cascade = [CascadeType.ALL])
    var offers: MutableList<Offer> = mutableListOf(),

    @ManyToOne(fetch = FetchType.EAGER)
    var faculty: Faculty = Faculty()

) : BaseEntity()

fun Subject.convertToDTO() = SubjectDTO(
    id = id,
    code = code,
    name = name,
    facultyId = faculty.id
)



