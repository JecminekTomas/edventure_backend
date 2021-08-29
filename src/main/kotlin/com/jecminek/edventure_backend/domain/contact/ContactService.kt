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

    fun create(contactRequest: ContactRequest): ContactResponse{
        return repository.save(contactRequest.convertToEntity()).convertToResponse()
    }

    fun getById(id: Long): Contact {
        return repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id: Long, contactRequest: ContactRequest): ContactResponse {
        val contact = getById(id)
        contact.contactType = contactRequest.contactType
        contact.value = contactRequest.value
        return repository.save(contact).convertToResponse()
    }

    fun delete(id: Long) {
        repository.delete(getById(id))
    }

    fun ContactRequest.convertToEntity() = Contact(
        value = value,
        contactType = contactType,
        contactOwner = userService.getById(ownerId)
    )
}

