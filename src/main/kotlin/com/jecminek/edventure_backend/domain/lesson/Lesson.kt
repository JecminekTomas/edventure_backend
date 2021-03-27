package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.user.User
import com.jecminek.edventure_backend.domain.user.UserDto
import com.jecminek.edventure_backend.domain.user.convertToDto
import javax.persistence.*

@Entity
@Table(name = "LESSON")
class Lesson(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override val id: Long = 0,
    var startTimestamp: Long = 0,
    var endTimestamp: Long = 0,
    var price: Double = 0.0,
    var online: Boolean = false,

    @ManyToMany(mappedBy = "teacherLessons")
    var teachers: MutableList<User> = mutableListOf(),

    @ManyToMany(mappedBy = "studentLessons")
    var students: MutableList<User> = mutableListOf()
) : BaseEntity()

fun convertUsersMutableListToUsersDtoMutableList(users: MutableList<User>): MutableList<UserDto> {
    val usersDto: MutableList<UserDto> = mutableListOf();
    for (user in users) {
        usersDto.add(user.convertToDto())
    }
    return usersDto
}

fun convertLessonsMutableListToLessonsDtoMutableList(lessons: MutableList<Lesson>): MutableList<LessonDto> {
    val lessonsDto: MutableList<LessonDto> = mutableListOf();
    for (lesson in lessons) {
        lessonsDto.add(lesson.convertToDto())
    }
    return lessonsDto
}

fun Lesson.convertToDto() =
    LessonDto(
        id = id,
        startTimestamp = startTimestamp,
        endTimestamp = endTimestamp,
        price = price,
        online = online,
        teachers = convertUsersMutableListToUsersDtoMutableList(teachers),
        students = convertUsersMutableListToUsersDtoMutableList(students)
    )



