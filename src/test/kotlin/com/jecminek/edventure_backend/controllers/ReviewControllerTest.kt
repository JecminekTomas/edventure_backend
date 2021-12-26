package com.jecminek.edventure_backend.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jecminek.edventure_backend.domain.review.ReviewRequest
import com.jecminek.edventure_backend.domain.review.ReviewResponse
import com.jecminek.edventure_backend.domain.review.ReviewService
import com.jecminek.edventure_backend.domain.score.ScoreBalance
import com.jecminek.edventure_backend.domain.subject.SubjectDTO
import com.jecminek.edventure_backend.domain.user.UserResponse
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.*

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ReviewControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var service: ReviewService

    private val exampleReviewRequest =
        ReviewRequest(3.0, "", true, 1)

    private val exampleReviewResponse =
        ReviewResponse(
            1,
            3.0,
            "",
            0,
            null,
            UserResponse(1, "Karel", "Novák", "xuser"),
        )

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `test find reviews method (no parameter)`() {
        every { service.findReviews(null, null, any()) } returns listOf(exampleReviewResponse)

        mockMvc.get("/api/reviews")
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$[0].id") { value(expectedValue = 1) }
                jsonPath("\$[0].stars") { value(expectedValue = 3.0) }
                jsonPath("\$[0].verbalEvaluation") { value(expectedValue = "") }
                jsonPath("\$[0].reviewTimestamp") { value(expectedValue = 0) }
                jsonPath("\$[0].userFrom") { value(expectedValue = null) }
                jsonPath("\$[0].userTo.id") { value(expectedValue = "1") }
                jsonPath("\$[0].userTo.firstName") { value(expectedValue = "Karel") }
                jsonPath("\$[0].userTo.lastName") { value(expectedValue = "Novák") }
                jsonPath("\$[0].userTo.userName") { value(expectedValue = "xuser") }
                jsonPath("\$[0].scoreBalance.helpfulCount") { value(expectedValue = "1") }
                jsonPath("\$[0].scoreBalance.unhelpfulCount") { value(expectedValue = "1") }
                jsonPath("\$[0].scoreBalance.userVote") { value(expectedValue = null) }
                jsonPath("\$[0].subject.id") { value(expectedValue = 1) }
                jsonPath("\$[0].subject.code") { value(expectedValue = "PTN") }
                jsonPath("\$[0].subject.name") { value(expectedValue = "Programovací techniky") }
                jsonPath("\$[0].subject.facultyId") { value(expectedValue = 1) }
            }

        verify(exactly = 1) { service.findReviews(null, null, any()) }
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `test find reviews method (userFromId parameter)`() {
        every { service.findReviews(1, null, any()) } returns listOf(exampleReviewResponse)

        mockMvc.get("/api/reviews?userFromId=1")
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$[0].id") { value(expectedValue = 1) }
                jsonPath("\$[0].stars") { value(expectedValue = 3.0) }
                jsonPath("\$[0].verbalEvaluation") { value(expectedValue = "") }
                jsonPath("\$[0].reviewTimestamp") { value(expectedValue = 0) }
                jsonPath("\$[0].userFrom") { value(expectedValue = null) }
                jsonPath("\$[0].userTo.id") { value(expectedValue = "1") }
                jsonPath("\$[0].userTo.firstName") { value(expectedValue = "Karel") }
                jsonPath("\$[0].userTo.lastName") { value(expectedValue = "Novák") }
                jsonPath("\$[0].userTo.userName") { value(expectedValue = "xuser") }
                jsonPath("\$[0].scoreBalance.helpfulCount") { value(expectedValue = "1") }
                jsonPath("\$[0].scoreBalance.unhelpfulCount") { value(expectedValue = "1") }
                jsonPath("\$[0].scoreBalance.userVote") { value(expectedValue = null) }
                jsonPath("\$[0].subject.id") { value(expectedValue = 1) }
                jsonPath("\$[0].subject.code") { value(expectedValue = "PTN") }
                jsonPath("\$[0].subject.name") { value(expectedValue = "Programovací techniky") }
                jsonPath("\$[0].subject.facultyId") { value(expectedValue = 1) }
            }

        verify(exactly = 1) { service.findReviews(1, null, any()) }
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `test find reviews method (userToId parameter)`() {
        every { service.findReviews(null, 1, any()) } returns listOf(exampleReviewResponse)

        mockMvc.get("/api/reviews?userToId=1")
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$[0].id") { value(expectedValue = 1) }
                jsonPath("\$[0].stars") { value(expectedValue = 3.0) }
                jsonPath("\$[0].verbalEvaluation") { value(expectedValue = "") }
                jsonPath("\$[0].reviewTimestamp") { value(expectedValue = 0) }
                jsonPath("\$[0].userFrom") { value(expectedValue = null) }
                jsonPath("\$[0].userTo.id") { value(expectedValue = "1") }
                jsonPath("\$[0].userTo.firstName") { value(expectedValue = "Karel") }
                jsonPath("\$[0].userTo.lastName") { value(expectedValue = "Novák") }
                jsonPath("\$[0].userTo.userName") { value(expectedValue = "xuser") }
                jsonPath("\$[0].scoreBalance.helpfulCount") { value(expectedValue = "1") }
                jsonPath("\$[0].scoreBalance.unhelpfulCount") { value(expectedValue = "1") }
                jsonPath("\$[0].scoreBalance.userVote") { value(expectedValue = null) }
                jsonPath("\$[0].subject.id") { value(expectedValue = 1) }
                jsonPath("\$[0].subject.code") { value(expectedValue = "PTN") }
                jsonPath("\$[0].subject.name") { value(expectedValue = "Programovací techniky") }
                jsonPath("\$[0].subject.facultyId") { value(expectedValue = 1) }
            }

        verify(exactly = 1) { service.findReviews(null, 1, any()) }
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `test create method`() {
        every { service.create(any(), exampleReviewRequest) } returns exampleReviewResponse

        mockMvc.post("/api/reviews") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleReviewRequest)
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isCreated() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$.id") { value(expectedValue = 1) }
                jsonPath("\$.stars") { value(expectedValue = 3.0) }
                jsonPath("\$.verbalEvaluation") { value(expectedValue = "") }
                jsonPath("\$.reviewTimestamp") { value(expectedValue = 0) }
                jsonPath("\$.userFrom") { value(expectedValue = null) }
                jsonPath("\$.userTo.id") { value(expectedValue = "1") }
                jsonPath("\$.userTo.firstName") { value(expectedValue = "Karel") }
                jsonPath("\$.userTo.lastName") { value(expectedValue = "Novák") }
                jsonPath("\$.userTo.userName") { value(expectedValue = "xuser") }
                jsonPath("\$.scoreBalance.helpfulCount") { value(expectedValue = "1") }
                jsonPath("\$.scoreBalance.unhelpfulCount") { value(expectedValue = "1") }
                jsonPath("\$.scoreBalance.userVote") { value(expectedValue = null) }
                jsonPath("\$.subject.id") { value(expectedValue = 1) }
                jsonPath("\$.subject.code") { value(expectedValue = "PTN") }
                jsonPath("\$.subject.name") { value(expectedValue = "Programovací techniky") }
                jsonPath("\$.subject.facultyId") { value(expectedValue = 1) }
            }

        verify(exactly = 1) { service.create(any(), exampleReviewRequest) }
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `test update method`() {
        every { service.update(1, any(), exampleReviewRequest) } returns exampleReviewResponse

        mockMvc.put("/api/reviews/1") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleReviewRequest)
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isAccepted() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$.id") { value(expectedValue = 1) }
                jsonPath("\$.stars") { value(expectedValue = 3.0) }
                jsonPath("\$.verbalEvaluation") { value(expectedValue = "") }
                jsonPath("\$.reviewTimestamp") { value(expectedValue = 0) }
                jsonPath("\$.userFrom") { value(expectedValue = null) }
                jsonPath("\$.userTo.id") { value(expectedValue = "1") }
                jsonPath("\$.userTo.firstName") { value(expectedValue = "Karel") }
                jsonPath("\$.userTo.lastName") { value(expectedValue = "Novák") }
                jsonPath("\$.userTo.userName") { value(expectedValue = "xuser") }
                jsonPath("\$.scoreBalance.helpfulCount") { value(expectedValue = "1") }
                jsonPath("\$.scoreBalance.unhelpfulCount") { value(expectedValue = "1") }
                jsonPath("\$.scoreBalance.userVote") { value(expectedValue = null) }
                jsonPath("\$.subject.id") { value(expectedValue = 1) }
                jsonPath("\$.subject.code") { value(expectedValue = "PTN") }
                jsonPath("\$.subject.name") { value(expectedValue = "Programovací techniky") }
                jsonPath("\$.subject.facultyId") { value(expectedValue = 1) }
            }

        verify(exactly = 1) { service.update(1, any(), exampleReviewRequest) }
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `test delete method`() {
        every { service.delete(1, any()) } returns Unit
        mockMvc.delete("/api/reviews/1")
            .andExpect { status { isNoContent() } }

        verify(exactly = 1) { service.delete(1, any()) }
    }

    //////////////////////////////////// UNAUTHORIZED ////////////////////////////////////////////////////

    @Test
    fun `test unauthorized user (findReviews)`() {
        every { service.findReviews(null, null, any()) } returns listOf(exampleReviewResponse)

        mockMvc.get("/api/reviews")
            .andExpect { status { isUnauthorized() } }

        verify(exactly = 0) { service.findReviews(null, null, any()) }
    }

    @Test
    fun `test unauthorized user (create)`() {
        every { service.create(any(), exampleReviewRequest) } returns exampleReviewResponse

        mockMvc.post("/api/reviews") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleReviewRequest)
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isUnauthorized() } }

        verify(exactly = 0) { service.create(any(), exampleReviewRequest) }
    }

    @Test
    fun `test unauthorized user (update)`() {
        every { service.update(1, any(), exampleReviewRequest) } returns exampleReviewResponse

        mockMvc.put("/api/reviews/1") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleReviewRequest)
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isUnauthorized() } }

        verify(exactly = 0) { service.update(1, any(), exampleReviewRequest) }
    }

    @Test
    fun `test unauthorized user (delete)`() {
        every { service.delete(1, any()) } returns Unit
        mockMvc.delete("/api/reviews/1")
            .andExpect { status { isUnauthorized() } }

        verify(exactly = 0) { service.delete(1, any()) }
    }
}