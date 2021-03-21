package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.domain.user.UserService
import com.jecminek.edventure_backend.domain.user.convertToEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class ReviewController {
    @Autowired
    lateinit var reviewService: ReviewService

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/reviews/reviewer/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findReviewsByReviewerId(@PathVariable id: Long): List<ReviewDto> =
        reviewService.findReviewsByReviewerId(id)

    @GetMapping("/reviews/reviewed/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findReviewsByReviewedId(@PathVariable id: Long): List<ReviewDto> =
        reviewService.findReviewsByReviewedId(id)

    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody review: ReviewDto,
        @RequestParam(required = true) reviewerId: Long,
        @RequestParam(required = true) reviewedId: Long
    ): ReviewDto{
        return reviewService.create(userService.findById(reviewerId), userService.findById(reviewedId), review)
    }

    @PutMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @PathVariable id: Long,
        @RequestBody review: ReviewDto
    ): ReviewDto = reviewService.update(id, review)

    @DeleteMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = reviewService.delete(id)
}