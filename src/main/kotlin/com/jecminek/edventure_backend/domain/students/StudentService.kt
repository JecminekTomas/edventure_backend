package com.jecminek.edventure_backend.domain.students

import com.jecminek.edventure_backend.domain.students.Student
import com.jecminek.edventure_backend.domain.students.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StudentService {
    // TODO: ADD UPDATE

    @Autowired
    lateinit var repository: StudentRepository

    fun findByLastName(name: String): List<Student> = repository.findByLastName(name)

    fun findAll(): MutableIterable<Student> = repository.findAll()

    fun save(student: Student): Student = repository.save(student)

    fun delete(id: Long) {
        val student = findByIdOrNull(id)
        if (student != null) {
            repository.delete(student)
        }
    }

    fun findByIdOrNull(id: Long): Student? = repository.findByIdOrNull(id)


}