package com.jecminek.edventure_backend.domain.score

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
@Tag(name = "Score", description = "Review's score")
class ScoreController {

    @Autowired
    lateinit var service: ScoreService

    @Operation(summary = "Find reviews by ID of review and ID of user")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ScoreDTO::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @GetMapping("/scores")
    @ResponseStatus(HttpStatus.OK)
    fun findByOwnerIdAndReviewId(
        @Parameter(
            description = "Id of user who created score",
            example = "1"
        ) @RequestParam userId: Long,
        @Parameter(
            description = "Id of review which score is part of",
            example = "1"
        ) @RequestParam reviewId: Long,
        @RequestHeader httpHeaders: HttpHeaders
    ): ScoreDTO = service.findByOwnerIdAndReviewId(userId, reviewId)

    @Operation(summary = "Create score")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ScoreDTO::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "403")
        ]
    )

    @PostMapping("/scores")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody scoreDTO: ScoreDTO, @RequestHeader httpHeaders: HttpHeaders): ScoreDTO =
        service.create(scoreDTO, httpHeaders)

    @Operation(summary = "Update score")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "202",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ScoreDTO::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "403"),
            ApiResponse(responseCode = "404")

        ]
    )
    @PutMapping("/scores/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(@PathVariable id: Long, @RequestBody scoreDTO: ScoreDTO, @RequestHeader httpHeaders: HttpHeaders) =
        service.update(id, scoreDTO, httpHeaders)

    @Operation(summary = "Delete score")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204"),
            ApiResponse(responseCode = "403"),
            ApiResponse(responseCode = "404")
        ]
    )
    @DeleteMapping("/scores/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long, @RequestHeader httpHeaders: HttpHeaders) = service.delete(id, httpHeaders)
}