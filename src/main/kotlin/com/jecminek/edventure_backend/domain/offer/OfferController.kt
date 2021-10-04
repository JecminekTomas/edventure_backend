package com.jecminek.edventure_backend.domain.offer

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "offer", description = "Offers can create offers")
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
        ) @PathVariable id: Long
    ): OfferDTO =
        offerService.findById(id).convertToDTO()

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
    fun create(@RequestBody offerDTO: OfferDTO): OfferDTO =
        offerService.create(offerDTO)

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
        @RequestBody offerDTO: OfferDTO
    ): OfferDTO = offerService.update(id, offerDTO)

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
    fun delete(@PathVariable id: Long) = offerService.delete(id)
}