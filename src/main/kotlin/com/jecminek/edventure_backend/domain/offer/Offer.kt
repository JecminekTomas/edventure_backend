package com.jecminek.edventure_backend.domain.offer

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.subject.Subject
import javax.persistence.*

@Entity
@Table(name = "OFFER")
class Offer(
    var price: Double = 0.0,
    var online: Boolean = false,
    var note: String = "",

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var subject: Subject = Subject()
) : BaseEntity()

fun Offer.convertToDTO() = OfferDTO(
    id = id,
    price = price,
    online = online,
    note = note
)