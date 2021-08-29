package com.jecminek.edventure_backend.domain.subject

import com.jecminek.edventure_backend.enums.Faculty
import com.jecminek.edventure_backend.enums.University
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SubjectService {

    @Autowired
    lateinit var repository: SubjectRepository

    fun findAll(): MutableIterable<Subject> = repository.findAll()

    fun findById(id: Long): Subject =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Subject With Id: $id, Not Found"
        )

    fun findSubjectByTitle(
        university: University,
        faculty: Faculty,
        title: String
    ): Subject = repository.findSubjectByUniversityAndFacultyAndTitle(university, faculty, title)

    fun findSubjectsByUniversityAndFaculty(university: University, faculty: Faculty): List<Subject> =
        repository.findSubjectsByUniversityAndFaculty(university, faculty)

    fun findSubjectsByUniversity(university: University): List<Subject> =
        repository.findSubjectsByUniversity(university)

    fun findByCode(code: String): Subject = repository.findSubjectByCode(code)

    fun create(subject: SubjectDTO): SubjectDTO = repository.save(subject.convertToEntity()).convertToDTO()

    fun update(id: Long, subject: SubjectDTO): SubjectDTO {
        val updatedSubject = findById(id)
        updatedSubject.code = subject.code
        updatedSubject.title = subject.title
        updatedSubject.faculty = subject.faculty
        updatedSubject.university = subject.university
        return repository.save(subject.convertToEntity()).convertToDTO()
    }

    fun delete(id: Long) = repository.delete(findById(id))


}