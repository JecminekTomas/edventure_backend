package com.jecminek.edventure_backend.domain.review

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : PagingAndSortingRepository<Review, Long> {
    fun findReviewsByReviewedId(reviewedId: Long, page: Pageable): Page<Review>
    fun findReviewsByReviewerId(reviewerId: Long, page: Pageable): Page<Review>
}