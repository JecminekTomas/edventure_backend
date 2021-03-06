package com.jecminek.edventure_backend.domain.review


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

    fun create(review: Review): Review =
        /**In request user is not capable to make review helpful, or unhelpful - it is bigger, but no need for new DTO*/
        repository.save(
            Review(
                stars = review.stars,
                verbalEvaluation = review.verbalEvaluation,
                reviewer = review.reviewer,
                reviewed = review.reviewed,
                reviewTimestamp = System.currentTimeMillis() / 1000L
            )
        )

    // FIXME: 21.03.2021 What if I forgot some argument?  Same as previous approach
    fun update(id: Long, updatedReview: Review): Review {
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