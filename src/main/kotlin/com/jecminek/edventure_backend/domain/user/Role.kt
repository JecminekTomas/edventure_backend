package com.jecminek.edventure_backend.domain.user

import org.springframework.security.core.GrantedAuthority

// TODO: 12.10.2021 REPAIR THIS!

class Role: GrantedAuthority {

    private val ADMIN = "ADMIN"
    private val USER = "USER"

    override fun getAuthority(): String {
        return USER
    }

}