package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.lesson.Lesson
import com.jecminek.edventure_backend.domain.review.Review
import com.jecminek.edventure_backend.enums.UserRole
import com.jecminek.edventure_backend.enums.UserStatus
import io.swagger.v3.oas.annotations.media.Schema
import javax.persistence.*
import javax.validation.constraints.*

@Entity
@Table(name = "\"USER\"")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override var id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var biography: String? = "",
    var phoneNumber: String? = "",

    @Enumerated(EnumType.STRING)
    var status: UserStatus = UserStatus.ONLINE,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_ROLE")
    @Enumerated(EnumType.STRING)
    @NotEmpty
    var roles: MutableList<UserRole> = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable( name = "STUDENT_LESSON", joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "lesson_id")]
    )
    var studentLessons: MutableList<Lesson>? = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable( name = "TEACHER_LESSON", joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "lesson_id")]
    )
    var teacherLessons: MutableList<Lesson> = mutableListOf(),

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "reviewer")
    var reviewerReviews: MutableList<Review> = mutableListOf(),

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "reviewed")
    var reviewedReviews: MutableList<Review> = mutableListOf()

) : BaseEntity()

fun User.convertToDto() = UserDto(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    biography = biography,
    phoneNumber = phoneNumber,
    roles = roles
)

fun User.convertToNameDto() = UserNameDto(
    id = id,
    firstName = firstName,
    lastName = lastName
)