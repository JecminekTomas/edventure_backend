package com.jecminek.edventure_backend.domain.teachers

import com.jecminek.edventure_backend.BaseEntity
import javax.persistence.Entity

@Entity
class Teacher(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var biography: String? = "",
    var rating: Double? = 2.5,
    var phoneNumber: String? = ""
): BaseEntity()