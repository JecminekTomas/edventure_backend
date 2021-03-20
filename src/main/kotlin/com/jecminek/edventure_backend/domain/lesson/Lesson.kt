package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.user.User
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "LESSON")
class Lesson(
    var startDateTime: Long = 0,
    var endDateTime: Long = 0,
    var price: Double = 0.0,
    var online: Boolean = false,

    @ManyToMany(mappedBy = "lessons")
    var users: MutableList<User> = mutableListOf()
): BaseEntity()