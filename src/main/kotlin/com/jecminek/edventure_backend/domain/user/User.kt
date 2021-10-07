package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.contact.Contact
import com.jecminek.edventure_backend.domain.offer.Offer
import com.jecminek.edventure_backend.domain.review.Review
import com.jecminek.edventure_backend.domain.score.Score
import javax.persistence.*

@Entity
@Table(name = "\"USER\"")
class User(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",

    // TODO: 27.08.2021 PASSWORD

    /** (DO NOT TOUCH) These lists are only for compatibility issue. **/

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewer", cascade = [CascadeType.ALL])
    var reviewerReviews: MutableList<Review> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewed", cascade = [CascadeType.ALL])
    var reviewedReviews: MutableList<Review> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = [CascadeType.ALL])
    var userContacts: MutableList<Contact> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = [CascadeType.ALL])
    var userOffers: MutableList<Offer> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = [CascadeType.ALL])
    var userScores: MutableList<Score> = mutableListOf()

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






