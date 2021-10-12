package com.jecminek.edventure_backend.domain.user

import org.springframework.security.core.GrantedAuthority

class Role: GrantedAuthority {

    val USER_ADMIN = "USER_ADMIN"
    val AUTHOR_ADMIN = "AUTHOR_ADMIN"
    val BOOK_ADMIN = "BOOK_ADMIN"

    override fun getAuthority(): String {
        return USER_ADMIN
    }
}