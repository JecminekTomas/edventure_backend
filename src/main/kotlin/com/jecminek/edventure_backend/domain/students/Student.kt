package com.jecminek.edventure_backend.domain.students

import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.lessons.Lesson
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Student(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var biography: String? = "",
    var rating: Double? = 2.5,
    var phoneNumber: String? = "",

    @OneToMany val lessons: List<Lesson> = emptyList()
): BaseEntity()