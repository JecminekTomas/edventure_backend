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
        val userToId = reviewService.findById(scoreDTO.reviewId).userTo.id

        if (findByOwnerIdAndReviewId(userId, scoreDTO.reviewId) != null)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT create more than one score per review")

        if (userId == userFromId)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT add score to own review.")

        if (userId == userToId)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT add score to review you got.")

        return repository.save(scoreDTO.convertToEntity(userId)).convertToDTO()
    }

    fun update(id: Long, scoreDTO: ScoreDTO, httpHeaders: HttpHeaders): ScoreDTO {
        val userId = jwtTokenUtil.getUserId(httpHeaders)
        val score = findById(id)

        if (userId != score.owner.id)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT update score which is not his own.")

        score.helpful = scoreDTO.helpful
        score.review = reviewService.findById(scoreDTO.reviewId)
        score.owner = userService.findById(userId)

        return repository.save(score).convertToDTO()
    }

    fun findById(id: Long): Score =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Score With Id: $id, Not Found"
        )

    fun findByOwnerIdAndReviewId(userId: Long, reviewId: Long) =
        repository.findScoreByOwnerIdAndReviewId(userId, reviewId)

    fun delete(id: Long, httpHeaders: HttpHeaders) {
        val userId = jwtTokenUtil.getUserId(httpHeaders)
        val score = findById(id)

        if (userId != score.owner.id)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT delete score which is not his own.")

        return repository.delete(findById(id))
    }

    fun getScoreBalance(reviewId: Long): ScoreBalance {
        val helpful = repository.countScoresByHelpfulIsTrueAndReviewId(reviewId)
        val unhelpful = repository.countScoresByHelpfulIsFalseAndReviewId(reviewId)
        return ScoreBalance(helpful, unhelpful)
    }


    fun ScoreDTO.convertToEntity(userId: Long) = Score(
        helpful = helpful,
        review = reviewService.findById(reviewId),
        owner = userService.findById(userId)
    )
}

