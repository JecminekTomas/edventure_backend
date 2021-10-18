package com.jecminek.edventure_backend.domain.contact

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "User - Contact", description = "User's contact")
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

    @PostMapping("/users/{userId}/contacts")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @PathVariable userId: Long,
        @RequestBody contactRequest: ContactRequest,
        @RequestHeader httpHeaders: HttpHeaders
    ): ContactResponse =
        contactService.create(userId, contactRequest, httpHeaders)

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
    @PutMapping("/users/{userId}/contacts/{contactId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @PathVariable contactId: Long,
        @PathVariable userId: Long,
        @RequestBody contactRequest: ContactRequest,
        @RequestHeader httpHeaders: HttpHeaders
    ): ContactResponse = contactService.update(userId, contactId, contactRequest, httpHeaders)

    @Operation(summary = "Delete contact")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204"),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @DeleteMapping("/users/{userId}/contacts/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @PathVariable contactId: Long,
        @PathVariable userId: Long,
        @RequestHeader httpHeaders: HttpHeaders
    ) = contactService.delete(userId, contactId, httpHeaders)

    @Operation(summary = "Find user's contacts")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ContactResponse::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @GetMapping("/users/{userId}/contacts")
    @ResponseStatus(HttpStatus.OK)
    fun findUserContacts(
        @Parameter(
            description = "Id of user to be found",
            example = "1"
        ) @PathVariable userId: Long
    ): List<ContactResponse> =
        contactService.findContactsByOwnerId(userId)
}