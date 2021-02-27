package com.jecminek.edventure_backend.domain.teachers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TeacherService {

    @Autowired
    lateinit var repository: TeacherRepository

    fun findByLastName(name: String): List<Teacher> = repository.findByLastName(name)

    fun findAll(): MutableIterable<Teacher> = repository.findAll()

    fun save(teacher: Teacher): Teacher = repository.save(teacher)

    fun delete(id: Long) {
        val teacher = findByIdOrNull(id)
        if (teacher != null) {
            repository.delete(teacher)
        }
    }

    fun findByIdOrNull(id: Long): Teacher? = repository.findByIdOrNull(id)


}