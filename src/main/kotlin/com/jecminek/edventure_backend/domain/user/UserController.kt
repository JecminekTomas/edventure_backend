package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.domain.lesson.Lesson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    @Autowired
    lateinit var service: UserService

    @GetMapping("/users")
    fun findUserByRole(role: UserRole): List<User>? = service.findUserByRole(role)

    @DeleteMapping("/users/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)

    @PostMapping("/users")
    fun create(
        @RequestParam(required = true) firstName: String,
        @RequestParam(required = true) lastName: String,
        @RequestParam(required = true) email: String,
        @RequestParam(required = false) biography: String,
        @RequestParam(required = false) phoneNumber: String,
        @RequestParam(required = true) roles: MutableList<UserRole>
        // Status se neposílá. Nastaví se v service na ONLINE. - Budoucí feature.
    ): User = service.create(firstName, lastName, email, biography, phoneNumber, roles)

    @PutMapping("/users/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestParam(required = false) firstName: String,
        @RequestParam(required = false) lastName: String,
        @RequestParam(required = false) email: String,
        @RequestParam(required = false) biography: String,
        @RequestParam(required = false) phoneNumber: String,
        @RequestParam(required = false) status: UserStatus,
        @RequestParam(required = false) roles: MutableList<UserRole>,
        @RequestParam(required = false) lessons: MutableList<Lesson>
    ) {
        service.update(id, firstName, lastName, email, biography, phoneNumber, status, roles, lessons)
    }
}