package com.jecminek.edventure_backend.domain.contact

import com.jecminek.edventure_backend.domain.user.UserService
import com.jecminek.edventure_backend.security.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ContactService {

    @Autowired
    lateinit var repository: ContactRepository

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    fun create(userId: Long, contactRequest: ContactRequest, httpHeaders: HttpHeaders): ContactResponse {
        val loggedUserId = jwtTokenUtil.getUserId(httpHeaders)

        if (userId != loggedUserId)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT create contact to other user")

        //Check user existence
        userService.findById(userId)

        return repository.save(contactRequest.convertToEntity(loggedUserId)).convertToResponse()
    }

    fun getById(contactId: Long): Contact {
        return repository.findByIdOrNull(contactId) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Contact not found"
        )
    }

    fun update(
        userId: Long,
        contactId: Long,
        contactRequest: ContactRequest,
        httpHeaders: HttpHeaders
    ): ContactResponse {

        val loggedUserId = jwtTokenUtil.getUserId(httpHeaders)

        if (userId != loggedUserId)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT update contact to other user")

        //CHECK USER EXISTENCE
        userService.findById(userId)

        val contact = getById(contactId)
        contact.contactType = contactRequest.contactType
        contact.value = contactRequest.value
        return repository.save(contact).convertToResponse()
    }

    fun delete(userId: Long, contactId: Long, httpHeaders: HttpHeaders) {
        val loggedUserId = jwtTokenUtil.getUserId(httpHeaders)

        if (userId != loggedUserId)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User CANNOT delete contact to other user")

        //CHECK USER EXISTENCE
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


    fun ContactRequest.convertToEntity(userId: Long) = Contact(
        value = value,
        contactType = contactType,
        owner = userService.findById(userId)
    )
}

