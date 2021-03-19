package com.jecminek.edventure_backend.domain.review


import com.jecminek.edventure_backend.domain.user.User
import org.mapstruct.factory.Mappers
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

    fun create(review: ReviewDto): Review =
        repository.save(review.convertToEntity())

    fun update(id: Long, reviewDto: ReviewDto): Review? {
        val oldReview = findByIdOrNull(id)
        val newReview = reviewDto.convertToEntity()
        if (oldReview != null) {
            oldReview.stars = newReview.stars
            oldReview.verbalEvaluation = newReview.verbalEvaluation
            oldReview.helpful = newReview.helpful
            oldReview.unhelpful = newReview.unhelpful
            oldReview.user = newReview.user
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found")
        }
        return newReview
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