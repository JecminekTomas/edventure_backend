package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.BaseEntity

import com.jecminek.edventure_backend.domain.user.User
import com.jecminek.edventure_backend.domain.user.convertToDto
import javax.persistence.*

@Entity
@Table(name = "REVIEW")
class Review(
    var stars: Int = 0,
    var verbalEvaluation: String = "",
    var helpful: Int = 0,
    var unhelpful: Int = 0,
    var reviewTimestamp: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_REVIEWED", joinColumns = [JoinColumn(name = "review_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    var reviewed: User = User(),

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_REVIEWER", joinColumns = [JoinColumn(name = "review_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    var reviewer: User = User()

): BaseEntity()

// FIXME: 20.03.2021 MUST SEND REVIEWER ID IN DTO, THEN CONVERT
fun Review.convertToDto() = ReviewDto(
    id= id,
    stars = stars,
    verbalEvaluation = verbalEvaluation,
    helpful = helpful,
    unhelpful = unhelpful,
    reviewer =  reviewer.convertToDto(),
    reviewed = reviewed.convertToDto()
)
