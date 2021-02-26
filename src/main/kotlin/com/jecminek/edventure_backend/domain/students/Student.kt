package com.jecminek.edventure_backend.domain.students

import com.jecminek.edventure_backend.BaseEntity

data class Student(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var biography: String? = "",
    var rating: Double? = -1.0,
    var phoneNumber: String? = "00123456789",
): BaseEntity()