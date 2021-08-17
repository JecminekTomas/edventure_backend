package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.domain.user.UserService
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
@Tag(name = "Reviews", description = "Students can review teachers")
class ReviewController {
    @Autowired
    lateinit var reviewService: ReviewService

    @Operation(summary = "Find reviews by ID of user, who is in position of reviewer in review")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ReviewDto::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @GetMapping("/reviews/reviewers/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findReviewsByReviewerId(
        @Parameter(
            description = "Id of user who is in position of reviewer",
            example = "1"
        ) @PathVariable id: Long,
        @Parameter(
            description = "Number of page, to be found. Default page size is 50.",
            example = "0"
        )
        @RequestParam(required = true) page: Int
    ): List<ReviewDto> = reviewService.findReviewsByReviewerId(id, page)

    @Operation(summary = "Find reviews by ID of user, who is in position of reviewed in review")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Review(s) found",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ReviewDto::class)))
                ]
            ),
            ApiResponse(responseCode = "400"),
            ApiResponse(responseCode = "404")
        ]
    )
    @GetMapping("/reviews/reviewed/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findReviewsByReviewedId(
        @Parameter(
            description = "Id of user who is in position of reviewed",
            example = "1"
        ) @PathVariable id: Long,
        @Parameter(
            description = "Number of page, to be found. Default page size is 50.",
            example = "0"
        )
        @RequestParam(required = true) page: Int
    ): List<ReviewDto> = reviewService.findReviewsByReviewedId(id, page)


    @Operation(summary = "Creation of review")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201", description = "Review created",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ReviewDto::class)))
                ]
            ),
            ApiResponse(responseCode = "400")
        ]
    )
    // FIXME: 31.03.2021 Reviewer = student/teacher, Reviewed = teacher/student
    // FIXME: 31.03.2021 Reviewer != Reviewed
    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody review: ReviewDto): ReviewDto = reviewService.create(review)

    @Operation(summary = "Update review")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "202", description = "Review updated",
                content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = ReviewDto::class)))
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
        @RequestBody review: ReviewDto
    ): ReviewDto = reviewService.update(id, review)

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
        @PathVariable id: Long
    ) = reviewService.delete(id)
}