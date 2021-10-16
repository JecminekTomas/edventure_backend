package com.jecminek.edventure_backend.domain.score

import com.jecminek.edventure_backend.domain.review.ReviewService
import com.jecminek.edventure_backend.domain.user.UserService
import com.jecminek.edventure_backend.security.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpHeaders
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

    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    fun create(scoreDTO: ScoreDTO, httpHeaders: HttpHeaders): ScoreDTO {
        val userId = jwtTokenUtil.getUserId(httpHeaders)
        val userFromId = reviewService.findById(scoreDTO.reviewId).userFrom.id

        if (repository.findScoreByUserId(userId).isNotEmpty())
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT create more than one score per review")

        if (userId != userFromId)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT add point to own review.")

        return repository.save(scoreDTO.convertToEntity()).convertToDTO()
    }

    fun update(id: Long, scoreDTO: ScoreDTO, httpHeaders: HttpHeaders): ScoreDTO {
        val userId = jwtTokenUtil.getUserId(httpHeaders)
        val score = findById(id)

        if (userId != score.user.id)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT update score is not his own.")

        if (repository.findScoreByUserId(userId).isEmpty())
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT update score when he did not create any.")

        score.helpful = scoreDTO.helpful
        score.review = reviewService.findById(scoreDTO.reviewId)
        score.user = userService.findById(userId)

        return repository.save(score).convertToDTO()
    }

    fun findById(id: Long): Score =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Score With Id: $id, Not Found"
        )

    fun findScoresByReviewId(reviewId: Long): List<ScoreDTO> =
        repository.findScoreByReviewId(reviewId).map { it.convertToDTO() }

    fun delete(id: Long, httpHeaders: HttpHeaders) {
        val userId = jwtTokenUtil.getUserId(httpHeaders)
        val score = findById(id)

        if (userId != score.user.id)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT update score is not his own.")

        return repository.delete(findById(id))
    }

    fun ScoreDTO.convertToEntity() = Score(
        helpful = helpful,
        review = reviewService.findById(reviewId)
    )
}

