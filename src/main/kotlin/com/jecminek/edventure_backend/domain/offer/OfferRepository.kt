package com.jecminek.edventure_backend.domain.offer

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OfferRepository : CrudRepository<Offer, Long> {

    @Query("SELECT * FROM offer o WHERE o.owner_id != ?1 ORDER BY random() LIMIT 50", nativeQuery = true)
    fun getOffersShowcase(userId: Long): List<Offer>

    fun findOfferByOwnerIdAndSubjectId(ownerId: Long, subjectId: Long): Offer?
    fun findOffersByOwnerId(ownerId: Long): List<Offer>

    fun findOffersBySubjectIdAndOwnerIdNot(subjectId: Long, ownerId: Long): List<Offer>
}