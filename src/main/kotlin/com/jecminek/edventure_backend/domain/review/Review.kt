package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.user.User
import javax.persistence.*

@Entity
@Table(name = "REVIEW")
class Review(
    var stars: Double = 0.0,
    var verbalEvaluation: String = "",
    var helpful: Int = 0,
    var unhelpful: Int = 0,
    var reviewTimestamp: Long = 0,

    /** Reviewer = Who is reviewing.
     * Reviewed = Who is being reviewed.*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reviewed_id")
    var reviewed: User = User(),

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reviewer_id")
    var reviewer: User = User()

) : BaseEntity()

fun Review.convertEntityToDto() = ReviewDto(
    id = id,
    stars = stars,
    verbalEvaluation = verbalEvaluation,
    helpful = helpful,
    unhelpful = unhelpful,
    reviewerId = reviewer.id,
    reviewedId = reviewed.id
)

