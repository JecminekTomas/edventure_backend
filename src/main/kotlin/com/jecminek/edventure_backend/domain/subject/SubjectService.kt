package com.jecminek.edventure_backend.domain.subject

import com.jecminek.edventure_backend.domain.faculty.FacultyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SubjectService {

    @Autowired
    lateinit var repository: SubjectRepository

    @Autowired
    lateinit var facultyService: FacultyService

    fun findAll(facultyId: Long?): List<SubjectDTO> =
        if (facultyId != null) repository.findSubjectsByFacultyId(facultyId).map { it.convertToDTO() }
        else repository.findAll().map { it.convertToDTO() }

    fun findById(id: Long): Subject =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Subject With Id: $id, Not Found"
        )

    fun getById(id: Long): SubjectDTO = findById(id).convertToDTO()


    fun create(subjectDTO: SubjectDTO): SubjectDTO = repository.save(subjectDTO.convertToEntity()).convertToDTO()

    fun update(id: Long, subjectDTO: SubjectDTO): SubjectDTO {
        val subject = findById(id)
        subject.code = subjectDTO.code
        subject.name = subjectDTO.name
        subject.faculty = facultyService.findById(subjectDTO.facultyId)
        return repository.save(subject).convertToDTO()
    }

    fun delete(id: Long) = repository.delete(findById(id))

    fun SubjectDTO.convertToEntity() = Subject(
        code = code,
        name = name,
        faculty = facultyService.findById(facultyId),
    )


}