package com.jecminek.edventure_backend.domain.faculty

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class FacultyService {
    @Autowired
    lateinit var repository: FacultyRepository

    // TODO: 06.10.2021 When university done, uncomment
//    fun findAll(universityId: Long?): MutableIterable<Faculty> =
//        if (universityId) repository.find repository.findAll()


    fun findById(id: Long): Faculty = repository.findByIdOrNull(id) ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Faculty With ID: $id, Not Found"
    )

    fun create(facultyDTO: FacultyDTO): FacultyDTO = repository.save(facultyDTO.convertToEntity()).convertToDTO()

    fun update(id: Long, facultyDTO: FacultyDTO): FacultyDTO {
        val faculty = findById(id)
        faculty.name = facultyDTO.name
        faculty.code = facultyDTO.code
        return repository.save(faculty).convertToDTO()
    }

    fun delete(id: Long) = repository.delete(findById(id))
}