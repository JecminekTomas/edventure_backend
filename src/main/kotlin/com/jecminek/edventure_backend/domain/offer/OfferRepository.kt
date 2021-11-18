package com.jecminek.edventure_backend.domain.offer

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OfferRepository : CrudRepository<Offer, Long> {
    fun findOfferByOwnerIdAndSubjectId(ownerId: Long, subjectId: Long): Offer?
    fun findOffersByOwnerId(ownerId: Long): List<Offer>
}