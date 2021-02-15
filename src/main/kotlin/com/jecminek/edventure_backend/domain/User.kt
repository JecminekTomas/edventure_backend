package com.jecminek.edventure_backend.domain

data class User(
    var id_user: Int,
    var firstName: String,
    var lastName: String,
    var email: String,
    var biography: String? = "",
    var rating: Double? = -1.0,
    var phone_number: String? = "00123456789"
)