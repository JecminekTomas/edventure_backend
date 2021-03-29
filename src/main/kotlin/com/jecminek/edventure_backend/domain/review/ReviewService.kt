package com.jecminek.edventure_backend.domain.review


import com.jecminek.edventure_backend.domain.user.UserDto
import com.jecminek.edventure_backend.domain.user.convertIdDtoToEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ReviewService {

    @Autowired
    lateinit var repository: ReviewRepository

    // TODO: 21.03.2021 ResponseException message

    fun findById(id: Long): Review =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun findReviewsByReviewedId(reviewed_id: Long): List<Review> =
        repository.findReviewsByReviewedId(reviewed_id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun findReviewsByReviewerId(reviewer_id: Long): List<Review> =
        repository.findReviewsByReviewerId(reviewer_id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(reviewer: UserDto, reviewed: UserDto, reviewDto: ReviewDto): Review =
        /**In request user is not capable to make review helpful, or unhelpful*/
        repository.save(
            Review(
                stars = reviewDto.stars,
                verbalEvaluation = reviewDto.verbalEvaluation,
                reviewer = reviewer.convertIdDtoToEntity(),
                reviewed = reviewed.convertIdDtoToEntity(),
                reviewTimestamp = System.currentTimeMillis() / 1000L
            )
        )

    // FIXME: 21.03.2021 What if I forgot some argument?  Same as previous approach
    fun update(id: Long, updatedReview: ReviewDto): Review {
        /**In request user is not capable to change reviewTimeStamp*/
        val review = findById(id)
        review.stars = updatedReview.stars
        review.verbalEvaluation = updatedReview.verbalEvaluation
        review.helpful = updatedReview.helpful
        review.unhelpful = updatedReview.unhelpful
        return repository.save(review)
        // TODO: 19.03.2021 oldReview.profilePicture = reviewDto.profilePicture
    }

        


    fun delete(id: Long) = repository.delete(findById(id))
}