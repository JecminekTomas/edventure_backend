package com.jecminek.edventure_backend.domain.offer

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface OfferRepository : PagingAndSortingRepository<Offer, Long> {
    fun findOfferByUserIdAndSubjectId(userId: Long, subjectId: Long): Offer?
}