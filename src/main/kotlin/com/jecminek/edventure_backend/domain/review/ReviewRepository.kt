package com.jecminek.edventure_backend.domain.review

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : PagingAndSortingRepository<Review, Long> {
    fun findReviewsByUserId(user_id: Long): List<Review>?
}