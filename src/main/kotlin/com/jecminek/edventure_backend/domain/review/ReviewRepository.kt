package com.jecminek.edventure_backend.domain.review

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : CrudRepository<Review, Long> {
    fun findReviewsByUserToId(userToId: Long): List<Review>
    fun findReviewsByUserFromId(userFromId: Long): List<Review>
    fun findReviewsByOfferIdAndUserFromId(offerId: Long, userId: Long): List<Review>

    @Query("SELECT AVG(r.stars) FROM Review r WHERE r.userTo.id = ?1")
    fun geReviewAverageStarsByUserId(userId: Long): Double?

    fun countReviewsByUserToId(userToId: Long): Int?
}