package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.offer.Offer
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

    /** Reviewer = Who is reviewing.
     * Reviewed = Who is being reviewed.
     * */

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "reviewed_id")
    var reviewed: User = User(),

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "reviewer_id")
    var reviewer: User = User(),

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    var offer: Offer = Offer()

) : BaseEntity()

fun Review.convertEntityToResponse() = ReviewResponse(
    id = id,
    stars = stars,
    verbalEvaluation = verbalEvaluation ?: "",
    reviewTimestamp = reviewTimestamp,
    reviewer = when {
        !anonymous -> reviewer.convertEntityToReviewResponse()
        else -> null
    },
    reviewed = reviewed.convertEntityToReviewResponse()
)

