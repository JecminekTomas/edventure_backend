package com.jecminek.edventure_backend.domain.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.lesson.Lesson
import com.jecminek.edventure_backend.domain.review.Review
import com.jecminek.edventure_backend.enums.UserRole
import javax.persistence.*
import javax.validation.constraints.NotEmpty

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

    /** TODO 28.3.21 - Future feature.
     * @Enumerated(EnumType.STRING)
     * var status: UserStatus = UserStatus.ONLINE,*/

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_ROLE")
    @Enumerated(EnumType.STRING)
    @NotEmpty
    var roles: MutableList<UserRole> = mutableListOf(),

    /** These lists are only for compatibility issue.
     *  This is the side mappedBy, so it is not primary, and there is
     *  explicitly said, there are FetchType lazy, which is default value.
     * */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
    var studentLessons: MutableList<Lesson>? = mutableListOf(),

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "teachers")
    private var teacherLessons: MutableList<Lesson> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewer")
    var reviewerReviews: MutableList<Review> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewed")
    var reviewedReviews: MutableList<Review> = mutableListOf()

) : BaseEntity()


fun User.convertEntityToDto(): UserDto = UserDto(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    biography = biography,
    phoneNumber = phoneNumber,
    roles = roles
)



