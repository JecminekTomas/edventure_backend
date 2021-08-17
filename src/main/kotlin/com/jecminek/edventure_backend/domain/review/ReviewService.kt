package com.jecminek.edventure_backend.domain.review


import com.jecminek.edventure_backend.constant.Constants
import com.jecminek.edventure_backend.domain.user.User
import com.jecminek.edventure_backend.domain.user.UserDto
import com.jecminek.edventure_backend.domain.user.UserService
import com.jecminek.edventure_backend.domain.user.convertDtoToEntity
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

    fun findById(id: Long): ReviewDto =
        repository.findByIdOrNull(id)?.convertEntityToDto() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun findReviewsByReviewedId(reviewed_id: Long, page: Int): List<ReviewDto> =
        repository.findReviewsByReviewedId(reviewed_id, PageRequest.of(page, Constants.defaultPageSize)).map {
            it.convertEntityToDto()
        }.content

    fun findReviewsByReviewerId(reviewer_id: Long, page: Int): List<ReviewDto> =
        repository.findReviewsByReviewerId(reviewer_id, PageRequest.of(page, Constants.defaultPageSize)).map {
            it.convertEntityToDto()
        }.content

    fun create(review: ReviewDto): ReviewDto =
        /**In request user is not capable to make review helpful, or unhelpful - it is bigger, but no need for new DTO*/
        repository.save(review.convertDtoToEntity()).convertEntityToDto()

    // FIXME: 17.08.2021 Update will be only on verbalEvaulation and stars
    // TODO: 17.08.2021 Create a new endpoint to send helpful or unhelpful

    fun update(id: Long, updatedReview: ReviewDto): ReviewDto {
        /**In request user is not capable to change reviewTimeStamp*/
        val review = findById(id)
        review.stars = updatedReview.stars
        review.verbalEvaluation = updatedReview.verbalEvaluation
        review.helpful = updatedReview.helpful
        review.unhelpful = updatedReview.unhelpful
        return repository.save(review.convertDtoToEntity()).convertEntityToDto()
    }

    fun delete(id: Long) = repository.delete(findById(id).convertDtoToEntity())

    fun ReviewDto.convertDtoToEntity() = Review(
        stars = stars,
        verbalEvaluation = verbalEvaluation,
        helpful = helpful,
        unhelpful = unhelpful,
        reviewTimestamp = System.currentTimeMillis() / 1000L,
        reviewer = userService.findById(reviewerId).convertDtoToEntity(),
        reviewed = userService.findById(reviewedId).convertDtoToEntity()
        // TODO: 19.03.2021 var userAvatar
    )
}