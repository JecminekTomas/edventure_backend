package com.jecminek.edventure_backend.domain.lessons

import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.students.Student
import com.jecminek.edventure_backend.domain.teachers.Teacher
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Lesson(
    var startDateTime: LocalDateTime = LocalDateTime.now(),
    var endDateTime: LocalDateTime = LocalDateTime.now(),
    var price: Double = 0.0,
    var online: Boolean = false,
    @ManyToOne var teacher: Teacher = Teacher(),
    @ManyToOne var student: Student = Student()
): BaseEntity()