package com.jecminek.edventure_backend.domain.faculty

import com.jecminek.edventure_backend.domain.university.UniversityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class FacultyService {
    @Autowired
    lateinit var repository: FacultyRepository

    @Autowired
    lateinit var universityService: UniversityService

    fun findAll(universityId: Long?): List<FacultyDTO> =
        if (universityId != null) repository.findFacultiesByUniversityId(universityId).map {
            it.convertToDTO()
        }
        else repository.findAll().map {
            it.convertToDTO()
        }.toList()


    fun findById(id: Long): Faculty = repository.findByIdOrNull(id) ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Faculty With ID: $id, Not Found"
    )

    fun getById(id: Long): FacultyDTO = findById(id).convertToDTO()

    fun create(facultyDTO: FacultyDTO): FacultyDTO = repository.save(facultyDTO.convertToEntity()).convertToDTO()

    fun update(id: Long, facultyDTO: FacultyDTO): FacultyDTO {
        val faculty = findById(id)
        faculty.name = facultyDTO.name
        faculty.code = facultyDTO.code
        faculty.university = universityService.findById(facultyDTO.universityId)
        return repository.save(faculty).convertToDTO()
    }

    fun delete(id: Long) = repository.delete(findById(id))

    fun FacultyDTO.convertToEntity() = Faculty(
        name = name,
        code = code,
        university = universityService.findById(universityId)
    )
}