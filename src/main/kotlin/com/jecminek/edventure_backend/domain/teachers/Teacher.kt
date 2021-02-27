package com.jecminek.edventure_backend.domain.teachers

import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.lessons.Lesson
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Teacher(
    firstName: String = "",
    lastName: String = "",
    email: String = "",
    biography: String? = "",
    rating: Double? = 2.5,
    phoneNumber: String? = "",
    @OneToMany val lessons: List<Lesson> = emptyList()
): BaseEntity()