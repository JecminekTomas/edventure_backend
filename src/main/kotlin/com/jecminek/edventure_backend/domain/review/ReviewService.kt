package com.jecminek.edventure_backend.domain.review


import com.jecminek.edventure_backend.constant.Constants
import com.jecminek.edventure_backend.domain.offer.OfferService
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

    @Autowired
    lateinit var offerService: OfferService

    fun findById(id: Long): Review =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun findReviewsByReviewedId(reviewed_id: Long, page: Int): List<ReviewResponse> =
        repository.findReviewsByReviewedId(reviewed_id, PageRequest.of(page, Constants.defaultPageSize)).map {
            it.convertEntityToResponse()
        }.content

    fun findReviewsByReviewerId(reviewer_id: Long, page: Int): List<ReviewResponse> =
        repository.findReviewsByReviewerId(reviewer_id, PageRequest.of(page, Constants.defaultPageSize)).map {
            it.convertEntityToResponse()
        }.content

    fun create(userId: Long, review: ReviewRequest): ReviewResponse =
        repository.save(review.convertRequestToEntity(userId)).convertEntityToResponse()

    fun update(id: Long, reviewRequest: ReviewRequest): ReviewResponse {
        val review = findById(id)
        review.stars = reviewRequest.stars
        review.verbalEvaluation = reviewRequest.verbalEvaluation
        review.anonymous = reviewRequest.anonymous
        return repository.save(review).convertEntityToResponse()
    }

    fun delete(id: Long) = repository.delete(findById(id))


    fun ReviewRequest.convertRequestToEntity(userId: Long) = Review(
        stars = stars,
        verbalEvaluation = verbalEvaluation,
        anonymous = anonymous,
        reviewTimestamp = System.currentTimeMillis(),
        reviewer = userService.findById(userId),
        reviewed = userService.findById(reviewedId),
        offer = offerService.findById(offerId)
    )

}