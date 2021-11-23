package com.jecminek.edventure_backend.domain.offer

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
@Tag(name = "Offer", description = "Users can create offers")
class OfferController {
    @Autowired
    lateinit var offerService: OfferService

    @Operation(summary = "Find offer by id")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = OfferDTO::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @GetMapping("/offers/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(
        @Parameter(
            description = "Id of offer to be found",
            example = "1"
        ) @PathVariable id: Long,
        @RequestHeader httpHeaders: HttpHeaders
    ): OfferDetailResponse =
        offerService.getById(id, httpHeaders)

    @Operation(summary = "Find all offers")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = OfferDTO::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @GetMapping("/offers")
    @ResponseStatus(HttpStatus.OK)
    fun getFirstOffers(@RequestHeader httpHeaders: HttpHeaders): List<OfferResponse> =
        offerService.getFirstOffers(httpHeaders)


    @Operation(summary = "Find offer by owner id")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = OfferDTO::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @GetMapping("/offers/by/{ownerId}")
    @ResponseStatus(HttpStatus.OK)
    fun findByOwnerId(
        @Parameter(
            description = "Id of offer owner",
            example = "1"
        ) @PathVariable ownerId: Long,
        @RequestHeader httpHeaders: HttpHeaders
    ): List<OfferDTO> =
        offerService.findByOwnerId(ownerId, httpHeaders)

    @Operation(summary = "Create offer")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = OfferDTO::class)))
                ]
            ),
            ApiResponse(responseCode = "400")
        ]
    )
    @PostMapping("/offers")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody offerDTO: OfferDTO, @RequestHeader httpHeaders: HttpHeaders): OfferDTO =
        offerService.create(offerDTO, httpHeaders)

    @Operation(summary = "Update offer")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "202",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = OfferDTO::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @PutMapping("/offers/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @PathVariable id: Long,
        @RequestBody offerDTO: OfferDTO,
        @RequestHeader httpHeaders: HttpHeaders
    ): OfferDTO = offerService.update(id, offerDTO, httpHeaders)

    @Operation(summary = "Delete offer")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204"),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @DeleteMapping("/offers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long, @RequestHeader httpHeaders: HttpHeaders) = offerService.delete(id, httpHeaders)
}