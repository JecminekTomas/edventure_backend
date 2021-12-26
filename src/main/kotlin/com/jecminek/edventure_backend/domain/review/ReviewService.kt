package com.jecminek.edventure_backend.domain.review


import com.jecminek.edventure_backend.domain.offer.OfferService
import com.jecminek.edventure_backend.domain.score.ScoreService
import com.jecminek.edventure_backend.domain.subject.SubjectService
import com.jecminek.edventure_backend.domain.subject.convertToDTO
import com.jecminek.edventure_backend.domain.user.UserService
import com.jecminek.edventure_backend.domain.user.convertEntityToResponse
import com.jecminek.edventure_backend.security.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpHeaders
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

    @Autowired
    lateinit var subjectService: SubjectService

    @Autowired
    lateinit var scoreService: ScoreService

    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    fun findById(id: Long): Review =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun findReviews(userFromId: Long?, userToId: Long?, httpHeaders: HttpHeaders): List<ReviewResponse> {
        val loggedUserId = jwtTokenUtil.getUserId(httpHeaders)
        return if (userFromId !== null && userToId === null)
            findReviewsByUserFromId(loggedUserId, userFromId, httpHeaders)
        else if (userFromId === null && userToId !== null)
            findReviewsByUserToId(userToId, httpHeaders)
        else if (userFromId === null && userToId === null)
            repository.findAll().map {
                it.convertEntityToResponse(loggedUserId, httpHeaders)
            }
        else
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
    }


    fun findReviewsByUserToId(userToId: Long, httpHeaders: HttpHeaders): List<ReviewResponse> =
        repository.findReviewsByUserToId(userToId).map {
            it.convertEntityToResponse(userToId, httpHeaders)
        }

    fun findReviewsByUserFromId(loggedUserId: Long, userFromId: Long, httpHeaders: HttpHeaders): List<ReviewResponse> {
        if (loggedUserId != userFromId)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT see reviews who wrote other user")

        return repository.findReviewsByUserFromId(userFromId).map {
            it.convertEntityToResponse(loggedUserId, httpHeaders)
        }
    }

    fun findReviewsByOfferIdAndUserFromId(offerId: Long, userFromId: Long): List<Review> =
        repository.findReviewsByOfferIdAndUserFromId(offerId, userFromId)

    fun create(httpHeaders: HttpHeaders, reviewRequest: ReviewRequest): ReviewResponse {

        isRequestValid(reviewRequest)

        val userId = jwtTokenUtil.getUserId(httpHeaders)
        val offerOwnerId = offerService.findById(reviewRequest.offerId).owner.id

        val alreadyReviewed = findReviewsByOfferIdAndUserFromId(reviewRequest.offerId, userId).isNotEmpty()

        if (alreadyReviewed) throw ResponseStatusException(
            HttpStatus.FORBIDDEN,
            "User CANNOT review offer more than once."
        )

        if (userId == offerOwnerId)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT review own offer.")

        return repository.save(reviewRequest.convertRequestToEntity(userId))
            .convertEntityToResponse(userId, httpHeaders)
    }

    fun reviewBalanceByUserId(userId: Long): ReviewBalance =
        ReviewBalance(
            repository.countReviewsByUserToId(userId) ?: 0,
            repository.geReviewAverageStarsByUserId(userId) ?: 0.0
        )


    fun update(id: Long, httpHeaders: HttpHeaders, reviewRequest: ReviewRequest): ReviewResponse {
        val userId = jwtTokenUtil.getUserId(httpHeaders)
        val review = findById(id)

        if (userId != review.userFrom.id)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User can update ONLY own reviews.")

        review.stars = reviewRequest.stars
        review.verbalEvaluation = reviewRequest.verbalEvaluation
        review.anonymous = reviewRequest.anonymous

        return repository.save(review).convertEntityToResponse(userId, httpHeaders)
    }

    fun delete(id: Long, httpHeaders: HttpHeaders) {
        val userId = jwtTokenUtil.getUserId(httpHeaders)
        val review = findById(id)

        if (userId != review.userFrom.id)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User can delete ONLY own reviews.")

        repository.delete(review)
    }

    fun isRequestValid(reviewRequest: ReviewRequest) {
        if (reviewRequest.stars > 5)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Review cannot have more than 5 stars")
        if (reviewRequest.stars < 1)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Review cannot have more less 1 star")
        if (reviewRequest.verbalEvaluation.length > 2048)
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Review's evaluation cannot have more than 2048 characters"
            )
    }

    fun ReviewRequest.convertRequestToEntity(userId: Long) = Review(
        stars = stars,
        verbalEvaluation = verbalEvaluation,
        anonymous = anonymous,
        reviewTimestamp = System.currentTimeMillis(),
        userFrom = userService.findById(userId),
        userTo = userService.findById(offerService.findById(offerId).owner.id),
        offer = offerService.findById(offerId)
    )

    fun Review.convertEntityToResponse(userId: Long, httpHeaders: HttpHeaders) = ReviewResponse(
        id = id,
        stars = stars,
        verbalEvaluation = verbalEvaluation ?: "",
        reviewTimestamp = reviewTimestamp,
        userFrom = userFrom.convertEntityToResponse(),
        userTo = userTo.convertEntityToResponse(),
        anonymous = anonymous,
        scoreBalance = scoreService.getScoreBalance(jwtTokenUtil.getUserId(httpHeaders), id),
        subject = subjectService.findById(offer!!.subject.id).convertToDTO()
    )

}