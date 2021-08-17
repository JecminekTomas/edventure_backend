package com.jecminek.edventure_backend.domain.subject

import com.jecminek.edventure_backend.enums.Faculty
import com.jecminek.edventure_backend.enums.University
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface SubjectRepository : PagingAndSortingRepository<Subject, Long> {
    fun findSubjectByUniversityAndFacultyAndTitle(
        university: University,
        faculty: Faculty,
        title: String
    ): Subject

    fun findSubjectsByUniversityAndFaculty(university: University, faculty: Faculty): List<Subject>
    fun findSubjectsByUniversity(university: University): List<Subject>

    fun findSubjectByCode(code: String): Subject
}