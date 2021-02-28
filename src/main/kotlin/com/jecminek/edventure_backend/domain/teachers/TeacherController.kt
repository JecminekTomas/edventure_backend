package com.jecminek.edventure_backend.domain.teachers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class TeacherController {

    @Autowired
    lateinit var service: TeacherService

    @GetMapping("/teachers")
    fun findAll(): MutableIterable<Teacher> = service.findAll()

    @GetMapping("/teachers/{lastName}")
    fun findByLastName(@PathVariable lastName: String): List<Teacher> = service.findByLastName(lastName)

    @DeleteMapping("/teachers/{id}/delete")
    fun delete(@PathVariable id: Long) = service.delete(id)

    @PostMapping("/teachers/create")
    fun create(@RequestBody teacher: Teacher): Teacher = service.save(teacher)

    @PutMapping("/teachers/{id}/update")
    fun update(@PathVariable id: Long, teacher: Teacher) {
        //FIXME: WHAT.. Přidá se na pouze konec.
        service.save(teacher)
    }
}