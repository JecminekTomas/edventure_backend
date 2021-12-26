package com.jecminek.edventure_backend.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jecminek.edventure_backend.domain.offer.OfferDTO
import com.jecminek.edventure_backend.domain.offer.OfferResponse
import com.jecminek.edventure_backend.domain.offer.OfferService
import com.jecminek.edventure_backend.domain.review.ReviewBalance
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
internal class OfferControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var service: OfferService

    private val exampleResponse =
        OfferResponse(
            1,
            100.0,
            "",
            "Karel",
            "Novák",
            ReviewBalance(1, 3.0),
            1,
            "Programovací techniky",
            "PTN"
        )

    private val exampleDTO =
        OfferDTO(
            1,
            100.0,
            true,
            "",
            1
        )

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `test find offers method without params`() {
        every { service.findOffers(any(), null, null, null) } returns listOf(exampleResponse)
        mockMvc.get("/api/api/offers")
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$[0].id") { value(expectedValue = 1) }
                jsonPath("\$[0].price") { value(expectedValue = 100.0) }
                jsonPath("\$[0].note") { value(expectedValue = "") }
                jsonPath("\$[0].teacherFirstName") { value(expectedValue = "Karel") }
                jsonPath("\$[0].teacherLastName") { value(expectedValue = "Novák") }
                jsonPath("\$[0].subjectId") { value(expectedValue = "1") }
                jsonPath("\$[0].reviewBalance.reviewCount") { value(expectedValue = "1") }
                jsonPath("\$[0].reviewBalance.starsAverage") { value(expectedValue = "3.0") }
                jsonPath("\$[0].subjectName") { value(expectedValue = "Programovací techniky") }
                jsonPath("\$[0].subjectCode") { value(expectedValue = "PTN") }
            }
        verify(exactly = 1) { service.findOffers(any(), null, null, null) }
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `test find offers method with ownerId parameter`() {
        every { service.findOffers(any(), 1, null, null) } returns
                listOf(exampleResponse)
        mockMvc.get("/api/offers?ownerId=1")
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$[0].id") { value(expectedValue = 1) }
                jsonPath("\$[0].price") { value(expectedValue = 100.0) }
                jsonPath("\$[0].note") { value(expectedValue = "") }
                jsonPath("\$[0].teacherFirstName") { value(expectedValue = "Karel") }
                jsonPath("\$[0].teacherLastName") { value(expectedValue = "Novák") }
                jsonPath("\$[0].subjectId") { value(expectedValue = "1") }
                jsonPath("\$[0].reviewBalance.reviewCount") { value(expectedValue = "1") }
                jsonPath("\$[0].reviewBalance.starsAverage") { value(expectedValue = "3.0") }
                jsonPath("\$[0].subjectName") { value(expectedValue = "Programovací techniky") }
                jsonPath("\$[0].subjectCode") { value(expectedValue = "PTN") }
            }
        verify(exactly = 1) { service.findOffers(any(), 1, null, null) }
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `test find offers method with subjectId parameter`() {
        every { service.findOffers(any(), null, 1, null) } returns
                listOf(exampleResponse)
        mockMvc.get("/api/offers?subjectId=1")
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$[0].id") { value(expectedValue = 1) }
                jsonPath("\$[0].price") { value(expectedValue = 100.0) }
                jsonPath("\$[0].note") { value(expectedValue = "") }
                jsonPath("\$[0].teacherFirstName") { value(expectedValue = "Karel") }
                jsonPath("\$[0].teacherLastName") { value(expectedValue = "Novák") }
                jsonPath("\$[0].subjectId") { value(expectedValue = "1") }
                jsonPath("\$[0].reviewBalance.reviewCount") { value(expectedValue = "1") }
                jsonPath("\$[0].reviewBalance.starsAverage") { value(expectedValue = "3.0") }
                jsonPath("\$[0].subjectName") { value(expectedValue = "Programovací techniky") }
                jsonPath("\$[0].subjectCode") { value(expectedValue = "PTN") }
            }

        verify(exactly = 1) { service.findOffers(any(), null, 1, null) }
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `Test create method`() {
        every { service.create(exampleDTO, any()) } returns
                exampleDTO

        mockMvc.post("/api/offers") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleDTO)
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isCreated() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$.id") { value(expectedValue = 1) }
                jsonPath("\$.price") { value(expectedValue = 100.0) }
                jsonPath("\$.note") { value(expectedValue = "") }
                jsonPath("\$.online") { value(expectedValue = "true") }
                jsonPath("\$.subjectId") { value(expectedValue = "1") }
            }

        verify(exactly = 1) { service.create(exampleDTO, any()) }
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `test update method`() {
        every { service.update(1, exampleDTO, any()) } returns
                exampleDTO

        mockMvc.put("/api/offers/1") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleDTO)
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isAccepted() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$.id") { value(expectedValue = 1) }
                jsonPath("\$.price") { value(expectedValue = 100.0) }
                jsonPath("\$.note") { value(expectedValue = "") }
                jsonPath("\$.online") { value(expectedValue = "true") }
                jsonPath("\$.subjectId") { value(expectedValue = "1") }
            }
        verify(exactly = 1) { service.update(1, exampleDTO, any()) }
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `test delete method`() {
        every { service.delete(1, any()) } returns Unit
        mockMvc.delete("/api/offers/1")
            .andExpect { status { isNoContent() } }

        verify(exactly = 1) { service.delete(1, any()) }
    }


    @Test
    fun `test unauthorized user (findOffers)`() {
        mockMvc.get("/api/offers")
            .andExpect { status { isUnauthorized() } }
    }

    @Test
    fun `test unauthorized user (create)`() {
        mockMvc.post("/api/offers") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleDTO)
            accept = MediaType.APPLICATION_JSON
        }
    }

    @Test
    fun `test unauthorized user (update)`() {
        mockMvc.put("/api/offers") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleDTO)
            accept = MediaType.APPLICATION_JSON
        }
    }

    @Test
    fun `test unauthorized user (delete)`() {
        mockMvc.delete("/api/offers/1")
            .andExpect { status { isUnauthorized() } }
    }

}