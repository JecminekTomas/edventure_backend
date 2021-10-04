package com.jecminek.edventure_backend.domain.subject

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.contact.Contact
import com.jecminek.edventure_backend.domain.offer.Offer
import com.jecminek.edventure_backend.enums.Faculty
import com.jecminek.edventure_backend.enums.University
import javax.persistence.*
import javax.validation.constraints.NotEmpty


@Entity
@Table(name = "\"SUBJECT\"")
class Subject(
    var code: String = "",
    var title: String = "",

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contactOwner")
    var offers: MutableList<Offer> = mutableListOf(),

    // TODO: DELETE

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



