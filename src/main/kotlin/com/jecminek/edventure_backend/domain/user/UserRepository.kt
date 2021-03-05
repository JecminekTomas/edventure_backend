package com.jecminek.edventure_backend.domain.user

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : PagingAndSortingRepository<User, Long> {
    fun findUserByRole(role: UserRole): List<User>?
}