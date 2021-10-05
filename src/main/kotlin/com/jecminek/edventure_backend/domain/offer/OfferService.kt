package com.jecminek.edventure_backend.domain.offer

import com.jecminek.edventure_backend.domain.subject.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class OfferService {
    @Autowired
    lateinit var repository: OfferRepository

    @Autowired
    lateinit var subjectService: SubjectService

    fun findAll(): MutableIterable<Offer> = repository.findAll()

    fun findById(id: Long): Offer =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Subject With Id: $id, Not Found"
        )

    fun create(offerDTO: OfferDTO): OfferDTO = repository.save(offerDTO.convertToEntity()).convertToDTO()

    fun update(id: Long, offerDTO: OfferDTO): OfferDTO {
        val offer= findById(id)
        offer.price = offerDTO.price
        offer.online = offerDTO.online
        offer.note = offerDTO.note
        return repository.save(offer).convertToDTO()
    }

    fun delete(id: Long) = repository.delete(findById(id))

    fun OfferDTO.convertToEntity() = Offer(
        price = price,
        online = online,
        note = note,
        subject = subjectService.findById(subjectId)
    )
}