package com.jecminek.edventure_backend.domain.score

import com.jecminek.edventure_backend.domain.review.ReviewService
import com.jecminek.edventure_backend.domain.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ScoreService {

    @Autowired
    lateinit var repository: ScoreRepository

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var reviewService: ReviewService

    fun create(scoreDTO: ScoreDTO): ScoreDTO = repository.save(scoreDTO.convertToEntity()).convertToDTO()

    fun update(id: Long, scoreDTO: ScoreDTO): ScoreDTO {
        val score = findById(id)
        score.helpful = scoreDTO.helpful
        score.review = reviewService.findById(scoreDTO.reviewId)
        score.user = userService.findById(scoreDTO.userId)
        return repository.save(score).convertToDTO()
    }

    fun findById(id: Long): Score =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Subject With Id: $id, Not Found"
        )

    fun findScoresByReviewId(reviewId: Long): List<ScoreDTO> =
        repository.findScoreByReviewId(reviewId).map { it.convertToDTO() }

    fun delete(id: Long) = repository.delete(findById(id))

    fun ScoreDTO.convertToEntity() = Score(
        helpful = helpful,
        user = userService.findById(userId),
        review = reviewService.findById(reviewId)
    )

}