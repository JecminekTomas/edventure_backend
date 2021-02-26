package com.jecminek.edventure_backend.domain.teachers

import com.jecminek.edventure_backend.BaseEntity

data class Teacher(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var biography: String? = "",
    var isTeacher: Boolean = false,
    var isStudent: Boolean = false,
    var rating: Double? = -1.0,
    var phoneNumber: String? = "00123456789",
): BaseEntity()