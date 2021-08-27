package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.review.Review
import com.jecminek.edventure_backend.domain.subject.Subject
import com.jecminek.edventure_backend.enums.UserRole
import javax.persistence.*

@Entity
@Table(name = "\"USER\"")
class User(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var biography: String? = "",
    var phoneNumber: String? = "",

    /** TODO 28.3.21 - Future feature.
     * @Enumerated(EnumType.STRING)
     * var status: UserStatus = UserStatus.ONLINE,*/

    // TODO: 18.08.2021 User avatar (number)

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_ROLE")
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    var roles: MutableList<UserRole> = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name = "student_subject",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "subject_id")]
    )
    var studiedSubjects: MutableList<Subject>? = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name = "teacher_subject",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "subject_id")]
    )
    var taughtSubjects: MutableList<Subject>? = mutableListOf(),

    /** (DO NOT TOUCH) These lists are only for compatibility issue. **/

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewer")
    var reviewerReviews: MutableList<Review> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewed")
    var reviewedReviews: MutableList<Review> = mutableListOf()

) : BaseEntity()

fun User.convertEntityToResponse() = UserResponse(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    biography = biography,
    phoneNumber = phoneNumber,
    taughtSubjects = taughtSubjects
)

fun User.convertEntityToReviewResponse() = UserReviewResponse(
    id = id,
    firstName = firstName,
    lastName = lastName
)






