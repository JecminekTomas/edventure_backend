package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.domain.user.User
import org.apache.catalina.mapper.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class ReviewController {
    @Autowired
    lateinit var service: ReviewService

    @GetMapping("/reviews")
    @ResponseStatus(HttpStatus.OK)
    fun findReviewsByUserId(@RequestParam(required = true) user_id: Long): List<Review>? =
        service.findReviewsByUserId(user_id)

    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody review: ReviewDto
    ): Review = service.create(review)

    @PutMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(
        @PathVariable id: Long,
        @RequestBody review: ReviewDto
    ): Review? = service.update(id, review)

    @DeleteMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)
}