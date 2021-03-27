package com.jecminek.edventure_backend.domain.review

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : PagingAndSortingRepository<Review, Long> {
    // FIXME: 27.03.2021 RETURNS REVIEW!!! 
    fun findReviewsByReviewedId(reviewedId: Long?): List<ReviewDto>?
    fun findReviewsByReviewerId(reviewerId: Long?): List<ReviewDto>?
}