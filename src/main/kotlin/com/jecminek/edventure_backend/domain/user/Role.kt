package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.enums.AuthorityType
import org.springframework.security.core.GrantedAuthority

// TODO: 12.10.2021 REPAIR THIS!

class Role(private val role: String): GrantedAuthority {

    override fun getAuthority(): String {
        return role
    }

}