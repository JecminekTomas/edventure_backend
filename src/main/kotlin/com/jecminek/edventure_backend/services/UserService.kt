package com.jecminek.edventure_backend.services

import com.jecminek.edventure_backend.domain.User
import org.springframework.stereotype.Service

import java.util.ArrayList

import javax.annotation.PostConstruct


@Service
class UserService {
    private var users: ArrayList<User> = ArrayList()
    @PostConstruct
    fun init() {
        val john = User(1, "John", "Smith","c")
        users.add(john)
        val jane = User(2, "Jane", "Johnson","a")
        users.add(jane)
        val kate = User(3, "Kate", "Jones", "b")
        users.add(kate)
    }

    val allUsers: List<User?>?
        get() = users

    fun getPersonById(id: Int): User? {
        return users
            .stream()
            .filter { user: User? -> user!!.id_user == id }
            ?.findFirst()
            ?.orElse(null)
    }

    fun createPerson(user: User): User {
        val maxId = users.stream().mapToInt(User::id_user).max()
        if (maxId.isPresent) {
            user.id_user = maxId.asInt + 1
        }
        return user
    }

    fun deletePerson(id: Int) {
        for (person in users) {
            if (person.id_user == id) {
                users.remove(person)
                return
            }
        }
    }
}