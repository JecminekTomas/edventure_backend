package com.jecminek.edventure_backend.domain.offer

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface OfferRepository : PagingAndSortingRepository<Offer, Long> {
    fun findOfferByOwnerIdAndSubjectId(ownerId: Long, subjectId: Long): Offer?
}