package com.jecminek.edventure_backend.domain.offer

import com.jecminek.edventure_backend.domain.review.ReviewResponse
import com.jecminek.edventure_backend.domain.review.ReviewService
import com.jecminek.edventure_backend.domain.subject.SubjectService
import com.jecminek.edventure_backend.domain.user.UserService
import com.jecminek.edventure_backend.security.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class OfferService {
    @Autowired
    lateinit var repository: OfferRepository

    @Autowired
    lateinit var subjectService: SubjectService

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var reviewService: ReviewService

    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    fun findAll(): MutableIterable<Offer> = repository.findAll()

    fun getFirstOffers(httpHeaders: HttpHeaders): List<OfferResponse> {
        val userId = jwtTokenUtil.getUserId(httpHeaders)
        return repository.getFirstOffers(userId).map {
            it.convertToResponse()
        }
    }

    fun findById(id: Long): Offer =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Offer With Id: $id, Not Found"
        )

    fun getById(id: Long, httpHeaders: HttpHeaders): OfferDetailResponse {
        val offer: Offer = findById(id)
        val offerResponse: OfferResponse = offer.convertToResponse()

        val reviews: List<ReviewResponse> = reviewService.findReviewsByUserToId(offer.owner.id, httpHeaders)

        return OfferDetailResponse(offerResponse, reviews)
    }

    fun findByUserIdAndSubjectId(userId: Long, subjectId: Long) =
        repository.findOfferByOwnerIdAndSubjectId(userId, subjectId)

    fun findByOwnerId(ownerId: Long, httpHeaders: HttpHeaders): List<OfferDTO> {
        val userId = jwtTokenUtil.getUserId(httpHeaders)

        if (userId != ownerId)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT see other user offers")

        return repository.findOffersByOwnerId(ownerId).map { it.convertToDTO() }
    }


    fun create(offerDTO: OfferDTO, httpHeaders: HttpHeaders): OfferDTO {
        val userId = jwtTokenUtil.getUserId(httpHeaders)

        if (findByUserIdAndSubjectId(userId, offerDTO.subjectId) != null)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT create more offers per subject")

        return repository.save(offerDTO.convertToEntity(userId)).convertToDTO()
    }

    fun update(id: Long, offerDTO: OfferDTO, httpHeaders: HttpHeaders): OfferDTO {
        val offer = findById(id)
        val userId = jwtTokenUtil.getUserId(httpHeaders)

        if (offer.owner.id != userId)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT update offer of which is not his own.")

        offer.price = offerDTO.price
        offer.online = offerDTO.online
        offer.note = offerDTO.note
        return repository.save(offer).convertToDTO()
    }

    fun delete(id: Long, httpHeaders: HttpHeaders) {
        val offer = findById(id)
        val userId = jwtTokenUtil.getUserId(httpHeaders)

        if (offer.owner.id != userId)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT delete offer of which is not his own.")

        return repository.delete(offer)
    }


    fun OfferDTO.convertToEntity(userId: Long) = Offer(
        price = price,
        online = online,
        note = note,
        subject = subjectService.findById(subjectId),
        owner = userService.findById(userId)
    )

    fun Offer.convertToResponse() = OfferResponse(
        id = id,
        price = price,
        note = note,
        teacherFirstName = userService.findById(owner.id).firstName,
        teacherLastName = userService.findById(owner.id).lastName,
        reviewBalance = reviewService.reviewBalanceByUserId(owner.id),
        subjectId = subject.id,
        subjectName = subject.name
    )
}