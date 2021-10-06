package com.jecminek.edventure_backend.domain.university

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UniversityService {

    @Autowired
    lateinit var repository: UniversityRepository

    fun findAll(): List<UniversityDTO> = repository.findAll().map { it.convertToDTO() }.toList()

    fun findById(id: Long): University = repository.findByIdOrNull(id) ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "University With ID: $id, Not Found"
    )

    fun getById(id: Long): UniversityDTO = findById(id).convertToDTO()

    fun create(universityDTO: UniversityDTO): UniversityDTO =
        repository.save(universityDTO.convertToEntity()).convertToDTO()

    fun update(id: Long, universityDTO: UniversityDTO): UniversityDTO {
        val university = findById(id)
        university.name = universityDTO.name
        return repository.save(university).convertToDTO()
    }

    fun delete(id: Long) = repository.delete(findById(id))
}