package com.jecminek.edventure_backend.domain.subject

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.lesson.Lesson
import com.jecminek.edventure_backend.enums.Faculty
import javax.persistence.*
import javax.validation.constraints.NotEmpty


@Entity
@Table(name = "\"SUBJECT\"")
class Subject(
    var code: String = "",
    var title: String = "",

    @Enumerated(EnumType.STRING)
    @NotEmpty
    var faculty: Faculty = Faculty.INSTITUTE_OF_LIFELONG_LEARNING,

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    var lessons: MutableList<Lesson> = mutableListOf()

) : BaseEntity()


