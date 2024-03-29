package com.jecminek.edventure_backend.domain.score

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ScoreRepository : CrudRepository<Score, Long> {
    fun findScoresByOwnerIdAndReviewId(userId: Long, reviewId: Long): List<Score>
    fun countScoresByHelpfulIsTrueAndReviewId(reviewId: Long): Long
    fun countScoresByHelpfulIsFalseAndReviewId(reviewId: Long): Long
}