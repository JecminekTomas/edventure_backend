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

    // TODO: 27.08.2021 PASSWORD

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
)

fun User.convertEntityToReviewResponse() = UserReviewResponse(
    id = id,
    firstName = firstName,
    lastName = lastName
)






