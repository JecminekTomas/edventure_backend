package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.offer.Offer
import com.jecminek.edventure_backend.domain.score.Score
import com.jecminek.edventure_backend.domain.user.User
import com.jecminek.edventure_backend.domain.user.convertEntityToReviewResponse
import javax.persistence.*


@Entity
@Table(name = "REVIEW")
class Review(
    var stars: Double = 0.0,
    var verbalEvaluation: String? = null,
    var reviewTimestamp: Long = 0,
    var anonymous: Boolean = false,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_to_id")
    var userTo: User = User(),

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_from_id")
    var userFrom: User = User(),

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    var offer: Offer? = Offer(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review", cascade = [CascadeType.ALL])
    var scores: MutableList<Score> = mutableListOf()

) : BaseEntity()

fun Review.convertEntityToResponse() = ReviewResponse(
    id = id,
    stars = stars,
    verbalEvaluation = verbalEvaluation ?: "",
    reviewTimestamp = reviewTimestamp,
    userFrom = when {
        !anonymous -> userFrom.convertEntityToReviewResponse()
        else -> null
    },
    userTo = userTo.convertEntityToReviewResponse()
)

