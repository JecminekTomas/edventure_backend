package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.contact.Contact
import com.jecminek.edventure_backend.domain.review.Review
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table

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
    var reviewedReviews: MutableList<Review> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contactOwner")
    var userContacts: MutableList<Contact> = mutableListOf()

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






