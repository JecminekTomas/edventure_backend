package com.jecminek.edventure_backend.domain.score

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.review.Review
import com.jecminek.edventure_backend.domain.user.User
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "SCORE")
class Score(
    var helpful: Boolean = false,

    @ManyToOne(fetch = FetchType.EAGER)
    var owner: User,

    @ManyToOne(fetch = FetchType.EAGER)
    var review: Review

) : BaseEntity()

fun Score.convertToDTO() = ScoreDTO(
    id = id,
    helpful = helpful,
    reviewId = review.id
)