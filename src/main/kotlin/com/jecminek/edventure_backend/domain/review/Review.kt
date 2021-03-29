package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.user.User
import javax.persistence.*

@Entity
@Table(name = "REVIEW")
class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override val id: Long = 0,
    var stars: Double = 0.0,
    var verbalEvaluation: String = "",
    // FIXME: 21.03.2021 IT IS STUPID TO SEND HELPFUL AND UNHELPFUL IN CREATE (It's possible to have more likes...)
    var helpful: Int = 0,
    var unhelpful: Int = 0,
    var reviewTimestamp: Long = 0,

    /** Reviewer = Who is reviewing.
     * Reviewed = Who is being reviewed.*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reviewer_id")
    var reviewed: User = User(),

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reviewed_id")
    var reviewer: User = User()

): BaseEntity()

fun Review.convertEntityToDto() = ReviewDto(
    id= id,
    stars = stars,
    verbalEvaluation = verbalEvaluation,
    helpful = helpful,
    unhelpful = unhelpful
)

// FIXME: 20.03.2021 MUST SEND REVIEWER ID IN DTO, THEN CONVERT

