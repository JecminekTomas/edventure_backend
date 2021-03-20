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

    fun findByIdOrNull(id: Long): ReviewDto =
        repository.findByIdOrNull(id)?.convertToDto() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun findReviewsByReviewedId(reviewed_id: Long): List<ReviewDto> =
        repository.findReviewsByReviewedId(reviewed_id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun findReviewsByReviewerId(reviewer_id: Long): List<ReviewDto> =
        repository.findReviewsByReviewerId(reviewer_id)?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(reviewDto: ReviewDto): ReviewDto =
        repository.save(reviewDto.convertToEntity()).convertToDto()

    fun update(id: Long, reviewDto: ReviewDto): ReviewDto {
        val oldReview = findByIdOrNull(id)
        oldReview.stars = reviewDto.stars
        oldReview.verbalEvaluation = reviewDto.verbalEvaluation
        oldReview.helpful = reviewDto.helpful
        oldReview.unhelpful = reviewDto.unhelpful
        // TODO: 19.03.2021 oldReview.profilePicture = reviewDto.profilePicture
        return repository.save(oldReview.convertToEntity()).convertToDto()
    }

    fun delete(id: Long) = repository.delete(findByIdOrNull(id).convertToEntity())
}