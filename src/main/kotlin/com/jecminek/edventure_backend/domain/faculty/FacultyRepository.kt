package com.jecminek.edventure_backend.domain.faculty

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FacultyRepository: CrudRepository<Faculty, Long> {
    fun findFacultiesByUniversityId(universityId: Long): List<Faculty>
}