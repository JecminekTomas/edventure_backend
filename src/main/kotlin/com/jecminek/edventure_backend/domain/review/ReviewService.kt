package com.jecminek.edventure_backend.domain.review


import com.jecminek.edventure_backend.domain.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException

@Service
class ReviewService {

    @Autowired
    lateinit var repository: ReviewRepository

    fun findByIdOrNull(id: Long): Review? = repository.findByIdOrNull(id)

    fun findReviewsByUserId(user_id: Long): List<Review>? = repository.findReviewsByUserId(user_id)

    fun create(stars: Int, verbalEvaluation: String, user: User): Review? =
        repository.save(Review(stars, verbalEvaluation, user = user))

    fun update(id: Long, stars: Int, verbalEvaluation: String, helpful: Int, unhelpful: Int, user: User): Review? {
        val review = findByIdOrNull(id)
        if (review != null) {
            review.stars = stars
            review.verbalEvaluation = verbalEvaluation
            review.helpful = helpful
            review.unhelpful = unhelpful
            review.user = user
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found")
        }
        return review
    }

    fun delete(id: Long) {
        val review = findByIdOrNull(id)
        if (review != null) {
            repository.delete(review)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found")
        }
    }


}