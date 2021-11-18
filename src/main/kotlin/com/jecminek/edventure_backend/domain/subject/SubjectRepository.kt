package com.jecminek.edventure_backend.domain.subject

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SubjectRepository : CrudRepository<Subject, Long> {
    fun findSubjectsByFacultyId(id: Long): List<Subject>
}