package com.jecminek.edventure_backend.domain.contact

import com.jecminek.edventure_backend.domain.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ContactService {

    @Autowired
    lateinit var repository: ContactRepository

    @Autowired
    lateinit var userService: UserService

    fun create(userId: Long, contactRequest: ContactRequest): ContactResponse {
        userService.findById(userId)
        return repository.save(contactRequest.convertToEntity()).convertToResponse()
    }

    fun getById(contactId: Long): Contact {
        return repository.findByIdOrNull(contactId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(userId: Long, contactId: Long, contactRequest: ContactRequest): ContactResponse {
        //CHECK USER EXISTENCE
        userService.findById(userId)

        val contact = getById(contactId)
        contact.contactType = contactRequest.contactType
        contact.value = contactRequest.value
        return repository.save(contact).convertToResponse()
    }

    fun delete(userId: Long, contactId: Long) {
        userService.findById(userId)
        repository.delete(getById(contactId))
    }

    fun findContactsByOwnerId(userId: Long): List<ContactResponse> {
        return repository.findContactsByOwnerId(userId).map {
            ContactResponse(
                id = it.id,
                contactType = it.contactType,
                value = it.value
            )
        }
    }


    fun ContactRequest.convertToEntity() = Contact(
        value = value,
        contactType = contactType,
        owner = userService.findById(ownerId)
    )
}

