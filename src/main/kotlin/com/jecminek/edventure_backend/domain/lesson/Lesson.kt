package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.user.User
import com.jecminek.edventure_backend.domain.user.UserStatus
import javax.persistence.*

@Entity
class Lesson(
    var startDateTime: Long = 0,
    var endDateTime: Long = 0,
    var price: Double = 0.0,
    var online: Boolean = false,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "USER_LESSONS")
    var users: MutableList<User> = mutableListOf()
): BaseEntity()