package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.user.User
import com.jecminek.edventure_backend.domain.user.UserDto
import com.jecminek.edventure_backend.domain.user.convertToDto
import com.jecminek.edventure_backend.domain.user.convertToEntity
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "LESSON")
class Lesson(
    var startTimestamp: Long = 0,
    var endTimestamp: Long = 0,
    var price: Double = 0.0,
    var online: Boolean = false,

    @ManyToMany(mappedBy = "teacherLessons")
    var teachers: MutableList<User> = mutableListOf(),

    @ManyToMany(mappedBy = "studentLessons")
    var students: MutableList<User> = mutableListOf()
): BaseEntity()

fun Lesson.convertToDto() = LessonDto(
        id = id,
        startTimestamp = startTimestamp,
        endTimestamp = endTimestamp,
        price = price,
        online = online)


