package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.lesson.Lesson
import javax.persistence.*

@Entity
class User(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var biography: String = "",
    var phoneNumber: String = "",

    @Enumerated(EnumType.STRING)
    var status: UserStatus = UserStatus.ONLINE,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "UserRole", joinColumns = [JoinColumn(name = "id")])
    @Enumerated(EnumType.STRING)
    var roles: MutableList<UserRole> = mutableListOf(),

    @ManyToMany(mappedBy = "users")
    var lessons: MutableList<Lesson> = mutableListOf()
) : BaseEntity()