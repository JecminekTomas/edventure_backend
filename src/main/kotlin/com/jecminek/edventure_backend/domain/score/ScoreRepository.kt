package com.jecminek.edventure_backend.domain.score

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ScoreRepository: CrudRepository<Score, Long>{
    fun findScoreByReviewId(reviewId: Long): List<Score>
    fun findScoreByUserIdAndReviewId(userId: Long, reviewId: Long): Score?
}