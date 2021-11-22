package com.jecminek.edventure_backend.domain.offer

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OfferRepository : CrudRepository<Offer, Long> {
    // TODO: 19.11.2021 Find ALL but only 50 ROWS with RAND fun
    fun findOfferByOwnerIdAndSubjectId(ownerId: Long, subjectId: Long): Offer?
    fun findOffersByOwnerId(ownerId: Long): List<Offer>
}