package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.BaseEntity

import com.jecminek.edventure_backend.domain.user.User
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Review(
    var stars: Int = 0,
    var verbalEvaluation: String = "",
    var helpful: Int = 0,
    var unhelpful: Int = 0,

    @ManyToOne
    var user: User = User()

) : BaseEntity()