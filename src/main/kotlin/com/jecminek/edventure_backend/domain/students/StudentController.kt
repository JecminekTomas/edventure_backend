package com.jecminek.edventure_backend.domain.students

import com.jecminek.edventure_backend.domain.students.Student
import com.jecminek.edventure_backend.domain.students.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class StudentController {

    @Autowired
    lateinit var service: StudentService

    @GetMapping("/students")
    fun findAll(): MutableIterable<Student> = service.findAll()

    @GetMapping("/students/{lastName}")
    fun findByLastName(@PathVariable lastName: String): List<Student> = service.findByLastName(lastName)

    @DeleteMapping("/students/{id}/delete")
    fun delete(@PathVariable id: Long) = service.delete(id)

    @PostMapping("/students/create")
    fun create(@RequestBody student: Student): Student = service.save(student)
}