package com.jecminek.edventure_backend.domain.review

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class ReviewController {
    @Autowired
    lateinit var service: ReviewService

    @GetMapping("/reviews/reviewer/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findReviewsByReviewerId(@PathVariable id: Long): List<ReviewDto> =
        service.findReviewsByReviewerId(id)

    @GetMapping("/reviews/reviewed/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findReviewsByReviewedId(@PathVariable id: Long): List<ReviewDto> =
        service.findReviewsByReviewedId(id)

    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody review: ReviewDto
    ): ReviewDto = service.create(review)

    @PutMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @PathVariable id: Long,
        @RequestBody review: ReviewDto
    ): ReviewDto = service.update(id, review)

    @DeleteMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)
}