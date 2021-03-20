package com.jecminek.edventure_backend.domain.review

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : PagingAndSortingRepository<Review, Long> {
    fun findReviewsByReviewedId(reviewedId: Long?): List<ReviewDto>?
    fun findReviewsByReviewerId(reviewerId: Long?): List<ReviewDto>?
}