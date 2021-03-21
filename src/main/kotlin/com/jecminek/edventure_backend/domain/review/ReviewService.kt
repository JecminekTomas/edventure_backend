package com.jecminek.edventure_backend.domain.review


import com.jecminek.edventure_backend.domain.user.UserDto
import com.jecminek.edventure_backend.domain.user.convertToEntity
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

    fun findById(id: Long): ReviewDto =
        repository.findByIdOrNull(id)?.convertToDto() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun findReviewsByReviewedId(reviewed_id: Long): List<ReviewDto> =
        repository.findReviewsByReviewedId(reviewed_id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun findReviewsByReviewerId(reviewer_id: Long): List<ReviewDto> =
        repository.findReviewsByReviewerId(reviewer_id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(reviewer: UserDto, reviewed: UserDto, reviewDto: ReviewDto): ReviewDto =
        repository.save(
            Review(
                stars = reviewDto.stars,
                verbalEvaluation = reviewDto.verbalEvaluation,
                reviewer = reviewer.convertToEntity(),
                reviewed = reviewed.convertToEntity()
            )
        ).convertToDto()


    // FIXME: 21.03.2021 What if I forgot some argument?  Same as previous approach
    fun update(id: Long, review: ReviewDto): ReviewDto =
        repository.save(
            Review(
                id = findById(id).id,
                stars = review.stars,
                verbalEvaluation = review.verbalEvaluation,
                helpful = review.helpful,
                unhelpful = review.unhelpful
            )
        ).convertToDto()
        // TODO: 19.03.2021 oldReview.profilePicture = reviewDto.profilePicture
        
    

    fun delete(id: Long) = repository.delete(findById(id).convertToEntity())
}