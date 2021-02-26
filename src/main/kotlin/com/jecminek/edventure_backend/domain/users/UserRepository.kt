package com.jecminek.edventure_backend.domain.users

import com.jecminek.edventure_backend.domain.teachers.Teacher
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository: PagingAndSortingRepository<User, Long> {
    fun findByLastName(name: String): List<User>
}