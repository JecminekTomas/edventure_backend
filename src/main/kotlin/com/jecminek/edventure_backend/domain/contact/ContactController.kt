package com.jecminek.edventure_backend.domain.contact

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "Contacts", description = "Contact to user")
class ContactController {
    @Autowired
    lateinit var contactService: ContactService


    @Operation(summary = "Create contact")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ContactResponse::class)))
                ]
            ),
            ApiResponse(responseCode = "400")
        ]
    )

    @PostMapping("/contacts")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody contactRequest: ContactRequest): ContactResponse =
        contactService.create(contactRequest)

    @Operation(summary = "Update contact")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "202",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ContactResponse::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @PutMapping("/contacts/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @PathVariable id: Long,
        @RequestBody contact: ContactRequest
    ): ContactResponse = contactService.update(id, contact)

    @Operation(summary = "Delete contact")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204"),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @DeleteMapping("/contacts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = contactService.delete(id)
}