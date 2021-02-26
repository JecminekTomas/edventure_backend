package com.jecminek.edventure_backend.domain.users

import javax.persistence.MappedSuperclass


@MappedSuperclass
abstract class User(
    open var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var biography: String? = "",
    var rating: Double? = -1.0,
    var phoneNumber: String? = "00123456789",
)