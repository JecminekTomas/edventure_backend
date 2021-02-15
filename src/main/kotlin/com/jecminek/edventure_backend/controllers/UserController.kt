package com.jecminek.edventure_backend.controllers

import com.jecminek.edventure_backend.domain.User
import com.jecminek.edventure_backend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/v2/persons/")
class UserController {
    private lateinit var userService: UserService

    @get:RequestMapping(method = [RequestMethod.GET], produces = ["application/json"])
    val allUsers: List<User?>?
        get() = userService.allUsers

    @RequestMapping(method = [RequestMethod.GET], path = ["/{id}"], produces = ["application/json"])
    fun getPersonById(@PathVariable id: Int): User? {
        return userService.getPersonById(id)
    }

    @RequestMapping(method = [RequestMethod.DELETE], path = ["/{id}"])
    fun deletePerson(@PathVariable id: Int) {
        userService.deletePerson(id)
    }

    @RequestMapping(method = [RequestMethod.POST], produces = ["application/json"])
    fun createPerson(@RequestBody user: User?): User {
        return userService.createPerson(user!!)
    }

    @Autowired
    fun setPersonService(userService: UserService) {
        this.userService = userService
    }
}