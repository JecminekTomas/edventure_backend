package com.jecminek.edventure_backend.domain.user

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
    fun create(@RequestBody firstName: String, lastName: String, email: String, biography: String, phoneNumber: String): User = service.save(firstName, lastName, email, biography, phoneNumber)

    @PutMapping("/users/{id}")
    fun update(@PathVariable id: Long,
               @RequestParam(required = false) firstName: String?,
               @RequestParam(required = false) lastName: String?,
               @RequestParam(required = false) email: String?,
               @RequestParam(required = false) biography: String?,
               @RequestParam(required = false) phoneNumber: String?) {
        //FIXME: WHAT.. Přidá se na pouze konec.
        service.update(id, firstName, lastName, email, biography, phoneNumber)
    }
}