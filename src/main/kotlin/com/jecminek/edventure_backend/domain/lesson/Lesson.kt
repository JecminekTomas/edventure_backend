package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.user.*
import javax.persistence.*

@Entity
@Table(name = "LESSON")
class Lesson(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override var id: Long = 0,
    var startTimestamp: Long = 0,
    var endTimestamp: Long = 0,
    var price: Double = 0.0,
    var online: Boolean = false,

    @ManyToMany(mappedBy = "teacherLessons")
    var teachers: MutableList<User> = mutableListOf(),

    @ManyToMany(mappedBy = "studentLessons")
    var students: MutableList<User> = mutableListOf()
) : BaseEntity()


// FIXME: 28.03.2021 Make an naming convention!




