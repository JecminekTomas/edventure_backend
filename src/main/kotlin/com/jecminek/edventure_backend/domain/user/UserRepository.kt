package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.enums.UserRole
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : PagingAndSortingRepository<User, Long> {
    fun findUserByRoles(role: UserRole): MutableList<User>?
}