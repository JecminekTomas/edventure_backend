package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.domain.user.UserDto
import com.jecminek.edventure_backend.domain.user.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class ReviewController {
    @Autowired
    lateinit var reviewService: ReviewService

    @Autowired
    lateinit var userService: UserService


    @Operation(summary = "Get reviews by ID of user, who is in position of reviewer in review")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Review(s) found", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ReviewDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(
                responseCode = "404",
                description = "Review(s) with this ID of user in position of reviewer not found",
                content = [Content()]
            )]
    )
    @GetMapping("/reviews/reviewers/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findReviewsByReviewerId(
        @Parameter(
            description = "Id of user who is in position of reviewer",
            example = "1"
        ) @PathVariable id: Long
    ): List<ReviewDto> =
        reviewService.findReviewsByReviewerId(id)

    @Operation(summary = "Get reviews by ID of user, who is in position of reviewed in review")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Review(s) found", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ReviewDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(
                responseCode = "404",
                description = "Review(s) with this ID of user in position of reviewed not found",
                content = [Content()]
            )]
    )
    @GetMapping("/reviews/reviewed/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findReviewsByReviewedId(
        @Parameter(
            description = "Id of user who is in position of reviewer",
            example = "1"
        ) @PathVariable id: Long
    ): List<ReviewDto> =
        reviewService.findReviewsByReviewedId(id)


    @Operation(summary = "Creation of review")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201", description = "Review created", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ReviewDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()])]
    )
    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody review: ReviewDto,
        @RequestParam(required = true) reviewerId: Long,
        @RequestParam(required = true) reviewedId: Long
    ): ReviewDto = reviewService.create(userService.findById(reviewerId), userService.findById(reviewedId), review)


    @Operation(summary = "Update review")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "202", description = "Review updated", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ReviewDto::class)))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Review with this ID not found", content = [Content()])]
    )
    @PutMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @Parameter(description = "ID of updated review", example = "1")
        @PathVariable id: Long,
        @RequestBody review: ReviewDto
    ): ReviewDto = reviewService.update(id, review)

    @Operation(summary = "Delete review")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Review deleted", content = [Content()]),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Review with this ID not found", content = [Content()])]
    )
    @DeleteMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @Parameter(description = "ID of deleted review", example = "1")
        @PathVariable id: Long
    ) =
        reviewService.delete(id)
}