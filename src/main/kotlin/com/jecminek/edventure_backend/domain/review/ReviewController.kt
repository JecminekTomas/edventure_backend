package com.jecminek.edventure_backend.domain.review

import com.jecminek.edventure_backend.domain.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@Controller
class ReviewController {
    @Autowired
    lateinit var service: ReviewService

    @GetMapping("/reviews")
    fun findReviewsByUserId(@RequestParam(required = true) user_id: Long): List<Review>? =
        service.findReviewsByUserId(user_id)

    @PostMapping("/reviews")
    fun create(
        @RequestParam(required = true) stars: Int,
        @RequestParam(required = false) verbalEvaluation: String,
        @RequestParam(required = true) user: User
    ): Review? = service.create(stars, verbalEvaluation, user)

    @PutMapping("/reviews/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestParam(required = true) stars: Int,
        @RequestParam(required = true) verbalEvaluation: String,
        @RequestParam(required = true) helpful: Int,
        @RequestParam(required = true) unhelpful: Int,
        @RequestParam(required = true) user: User
    ): Review? = service.update(id, stars, verbalEvaluation, helpful, unhelpful, user)

    @DeleteMapping("/reviews/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}