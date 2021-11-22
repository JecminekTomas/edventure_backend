package com.jecminek.edventure_backend.domain.review

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
@Tag(name = "Review", description = "Users can review other users")
class ReviewController {
    @Autowired
    lateinit var reviewService: ReviewService

    @Operation(summary = "Find reviews by ID of user, where user write review")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ReviewResponse::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @GetMapping("/reviews/from/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun findReviewsByUserFrom(
        @Parameter(
            description = "ID of user, who wrote review",
            example = "1"
        ) @PathVariable userId: Long,
        @RequestHeader httpHeaders: HttpHeaders
    ): List<ReviewResponse> = reviewService.findReviewsByUserFromId(userId, httpHeaders)

    @Operation(summary = "Find reviews by ID of user, where user got review")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ReviewResponse::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @GetMapping("/reviews/to/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun findReviewsByUserTo(
        @Parameter(
            description = "ID of user, who review is for",
            example = "1"
        ) @PathVariable userId: Long,
        @RequestHeader httpHeaders: HttpHeaders
    ): List<ReviewResponse> = reviewService.findReviewsByUserToId(userId, httpHeaders)

    @Operation(summary = "Create review")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201", description = "Review created",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ReviewResponse::class)))
                ]
            ),
            ApiResponse(responseCode = "400")
        ]
    )
    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody reviewRequest: ReviewRequest,
        @RequestHeader httpHeaders: HttpHeaders
    ): ReviewResponse = reviewService.create(httpHeaders, reviewRequest)


    @Operation(summary = "Update review")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "202",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ReviewResponse::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @PutMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @Parameter(description = "ID of updated review", example = "1")
        @PathVariable id: Long,
        @RequestHeader httpHeaders: HttpHeaders,
        @RequestBody reviewRequest: ReviewRequest
    ): ReviewResponse = reviewService.update(id, httpHeaders, reviewRequest)

    @Operation(summary = "Delete review")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204"),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @DeleteMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @Parameter(description = "ID of deleted review", example = "1")
        @PathVariable id: Long,
        @RequestHeader httpHeaders: HttpHeaders
    ) = reviewService.delete(id, httpHeaders)
}