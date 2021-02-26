package com.jecminek.edventure_backend.domain.teachers

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherRepository: PagingAndSortingRepository<Teacher, Long>{

    fun findByLastName(name: String): List<Teacher>

}
