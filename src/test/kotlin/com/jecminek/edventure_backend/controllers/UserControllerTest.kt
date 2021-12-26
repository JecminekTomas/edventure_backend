package com.jecminek.edventure_backend.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jecminek.edventure_backend.domain.user.TokenResponse
import com.jecminek.edventure_backend.domain.user.UserResponse
import com.jecminek.edventure_backend.domain.user.UserService
import com.jecminek.edventure_backend.domain.user.request.ChangePasswordRequest
import com.jecminek.edventure_backend.domain.user.request.LoginRequest
import com.jecminek.edventure_backend.domain.user.request.RegisterRequest
import com.jecminek.edventure_backend.domain.user.request.UpdateProfileRequest
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UserControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var service: UserService

    private val exampleTokenResponse =
        TokenResponse("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLCB4bWFyZWssIE1hcmVrLCBOb3Z5IiwiaWF0IjoxNjM5ODQwMzU1LCJleHAiOjE2NDA0NDUxNTV9.qTZHz0DahPg5FXjcfYMX9-cyzubRYSo_jpNfXzzlUxWl1iW3-V3YrT56iBbZXsvUDIaksyBfKyOMYsJBR5tnqQ")

    private val exampleUserResponse =
        UserResponse(1, "Karel", "Novák", "xuser")

    private val exampleLoginRequest = LoginRequest("xuser", "password")

    private val exampleChangePasswordRequest =
        ChangePasswordRequest("xuser", "password", "newPassword")

    private val exampleRegisterRequest =
        RegisterRequest("Karel", "Novák", "xuser", "password", null)

    private val exampleUpdateProfileRequest =
        UpdateProfileRequest(1, "Karel", "Novák")

    @Test
    fun `test user registration`() {
        every { service.register(exampleRegisterRequest) } returns exampleUserResponse

        mockMvc.post("/api/register") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleRegisterRequest)
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isCreated() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$.id") { value(expectedValue = 1) }
                jsonPath("\$.firstName") { value(expectedValue = "Karel") }
                jsonPath("\$.lastName") { value(expectedValue = "Novák") }
                jsonPath("\$.userName") { value(expectedValue = "xuser") }
            }
        verify(exactly = 1) { service.register(exampleRegisterRequest) }
    }

    @Test
    fun `test login`() {
        every { service.login(exampleLoginRequest) } returns ResponseEntity.ok().body(exampleTokenResponse)

        mockMvc.post("/api/login") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleLoginRequest)
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$.token") {
                    value(expectedValue = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLCB4bWFyZWssIE1hcmVrLCBOb3Z5IiwiaWF0IjoxNjM5ODQwMzU1LCJleHAiOjE2NDA0NDUxNTV9.qTZHz0DahPg5FXjcfYMX9-cyzubRYSo_jpNfXzzlUxWl1iW3-V3YrT56iBbZXsvUDIaksyBfKyOMYsJBR5tnqQ")
                }
            }

        verify(exactly = 1) { service.login(exampleLoginRequest) }
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `test update of profile`() {
        every { service.updateProfile(exampleUpdateProfileRequest) } returns exampleTokenResponse

        mockMvc.put("/api/profile") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleUpdateProfileRequest)
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isAccepted() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$.token") {
                    value(expectedValue = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLCB4bWFyZWssIE1hcmVrLCBOb3Z5IiwiaWF0IjoxNjM5ODQwMzU1LCJleHAiOjE2NDA0NDUxNTV9.qTZHz0DahPg5FXjcfYMX9-cyzubRYSo_jpNfXzzlUxWl1iW3-V3YrT56iBbZXsvUDIaksyBfKyOMYsJBR5tnqQ")
                }
            }
        verify(exactly = 1) { service.updateProfile(exampleUpdateProfileRequest) }

    }

    @Test
    @WithMockUser(username = "user", password = "password")
    fun `test of password change`() {
        every { service.changePassword(exampleChangePasswordRequest) } returns Unit

        mockMvc.put("/api/profile/change_password") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleChangePasswordRequest)
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isAccepted() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$.token") {
                    value(expectedValue = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLCB4bWFyZWssIE1hcmVrLCBOb3Z5IiwiaWF0IjoxNjM5ODQwMzU1LCJleHAiOjE2NDA0NDUxNTV9.qTZHz0DahPg5FXjcfYMX9-cyzubRYSo_jpNfXzzlUxWl1iW3-V3YrT56iBbZXsvUDIaksyBfKyOMYsJBR5tnqQ")
                }
            }

        verify(exactly = 1) { service.changePassword(exampleChangePasswordRequest) }
    }

//////////////////////////////////// UNAUTHORIZED ////////////////////////////////////////////////////

    @Test
    fun `test unauthorized user (profile update)`() {
        every { service.updateProfile(exampleUpdateProfileRequest) } returns exampleTokenResponse

        mockMvc.put("/api/profile") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleUpdateProfileRequest)
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isUnauthorized() } }
        verify(exactly = 0) { service.updateProfile(exampleUpdateProfileRequest) }
    }

    @Test
    fun `test unauthorized user (change password)`() {
        every { service.changePassword(exampleChangePasswordRequest) } returns Unit

        mockMvc.put("/api/profile/change_password") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(exampleChangePasswordRequest)
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isUnauthorized() } }

        verify(exactly = 0) { service.changePassword(exampleChangePasswordRequest) }
    }
}