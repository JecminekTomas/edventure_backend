package com.jecminek.edventure_backend.domain.teachers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TeacherService {

    @Autowired
    lateinit var repository: TeacherRepository

    fun findByLastName(name: String): List<Teacher> {
        return repository.findByLastName(name)
    }

    fun findAll(): MutableIterable<Teacher> {
        return repository.findAll()
    }

    fun save(teacher: Teacher): Teacher {
        return repository.save(teacher)
    }

    fun delete(id: Long) {
        val teacher = findByIdOrNull(id)
        if (teacher != null) {
            repository.delete(teacher)
        }
    }

    fun findByIdOrNull(id: Long): Teacher? {
        return repository.findByIdOrNull(id)
    }


}