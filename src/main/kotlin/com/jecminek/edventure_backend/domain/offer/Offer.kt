package com.jecminek.edventure_backend.domain.offer

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.review.Review
import com.jecminek.edventure_backend.domain.subject.Subject
import com.jecminek.edventure_backend.domain.user.User
import javax.persistence.*

@Entity
@Table(name = "OFFER")
class Offer(
    var price: Double = 0.0,
    var online: Boolean = false,
    @Column(columnDefinition = "TEXT")
    var note: String? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    var subject: Subject = Subject(),

    @ManyToOne(fetch = FetchType.EAGER)
    var owner: User = User(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offer")
    var reviews: MutableList<Review> = mutableListOf()
) : BaseEntity()

fun Offer.convertToDTO() = OfferDTO(
    id = id,
    price = price,
    online = online,
    note = note,
    subjectId = subject.id
)