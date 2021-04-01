package com.jecminek.edventure_backend.domain.lesson

import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.subject.Subject
import com.jecminek.edventure_backend.domain.user.User
import javax.persistence.*

@Entity
@Table(name = "LESSON")
class Lesson(
    var startTimestamp: Long = 0,
    var endTimestamp: Long = 0,
    var price: Double = 0.0,
    var place: String? = "",
    var note: String? = "",

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "TEACHER_LESSON", joinColumns = [JoinColumn(name = "lesson_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    var teachers: MutableList<User> = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "STUDENT_LESSON", joinColumns = [JoinColumn(name = "lesson_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    var students: MutableList<User> = mutableListOf(),

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    var subject: Subject = Subject()

) : BaseEntity()




