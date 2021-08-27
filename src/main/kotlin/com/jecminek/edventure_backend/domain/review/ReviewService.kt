package com.jecminek.edventure_backend.domain.review


import com.jecminek.edventure_backend.constant.Constants
import com.jecminek.edventure_backend.domain.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ReviewService {

    @Autowired
    lateinit var repository: ReviewRepository

    @Autowired
    lateinit var userService: UserService

    fun findById(id: Long): ReviewResponse =
        repository.findByIdOrNull(id)?.convertEntityToResponse() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getById(id: Long): Review =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun findReviewsByReviewedId(reviewed_id: Long, page: Int): List<ReviewResponse> =
        repository.findReviewsByReviewedId(reviewed_id, PageRequest.of(page, Constants.defaultPageSize)).map {
            it.convertEntityToResponse()
        }.content

    fun findReviewsByReviewerId(reviewer_id: Long, page: Int): List<ReviewResponse> =
        repository.findReviewsByReviewerId(reviewer_id, PageRequest.of(page, Constants.defaultPageSize)).map {
            it.convertEntityToResponse()
        }.content

    fun create(review: ReviewRequest): ReviewResponse =
        repository.save(review.convertRequestToEntity()).convertEntityToResponse()

    // TODO: 17.08.2021 Create a new endpoint to send helpful or unhelpful

    fun update(id: Long, updatedReview: ReviewRequest): ReviewResponse {
        val review = getById(id)
        review.stars = updatedReview.stars
        review.verbalEvaluation = updatedReview.verbalEvaluation
        return repository.save(review).convertEntityToResponse()
    }

    fun delete(id: Long) = repository.delete(getById(id))

    fun ReviewRequest.convertRequestToEntity() = Review(
        stars = stars,
        verbalEvaluation = verbalEvaluation,
        reviewTimestamp = System.currentTimeMillis() / 1000L,
        reviewer = userService.getById(reviewerId),
        reviewed = userService.getById(reviewedId)
    )

}