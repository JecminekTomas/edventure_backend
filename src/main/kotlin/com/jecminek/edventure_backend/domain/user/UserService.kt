package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.domain.user.request.ChangePasswordRequest
import com.jecminek.edventure_backend.domain.user.request.LoginRequest
import com.jecminek.edventure_backend.domain.user.request.RegisterRequest
import com.jecminek.edventure_backend.domain.user.request.UpdateProfileRequest
import com.jecminek.edventure_backend.enums.AuthorityType
import com.jecminek.edventure_backend.security.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid
import javax.xml.bind.ValidationException

@Service
class UserService : UserDetailsService {

    @Autowired
    lateinit var repository: UserRepository

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder


    fun findById(id: Long): User = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun register(registerRequest: RegisterRequest): UserResponse {
        if (repository.findUserByUserName(registerRequest.userName) != null)
            throw ValidationException("Username exists!")
        else if (!areCredentialsRight(registerRequest))
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        else {
            val user = User(
                userName = registerRequest.userName,
                firstName = registerRequest.firstName,
                lastName = registerRequest.lastName,
                password = passwordEncoder.encode(registerRequest.password),
                userContacts = registerRequest.contacts?.toMutableList() ?: mutableListOf(),
                authority = AuthorityType.USER.toString(),
                locked = false,
                expired = false,
                enabled = true
            )
            return repository.save(user).convertEntityToResponse()
        }
    }

    fun login(@RequestBody request: @Valid LoginRequest): ResponseEntity<TokenResponse> {
        return try {
            val authenticate: Authentication = authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken(request.userName, request.password))
            val user = authenticate.principal as User

            ResponseEntity.ok()
                .body(TokenResponse(jwtTokenUtil.generateAccessToken(user)))

        } catch (ex: BadCredentialsException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

    fun changePassword(changePasswordRequest: ChangePasswordRequest) {
        try {
            val authenticate: Authentication = authenticationManager
                .authenticate(
                    UsernamePasswordAuthenticationToken(
                        changePasswordRequest.userName,
                        changePasswordRequest.oldPassword
                    )
                )
            val user = authenticate.principal as User

            // New password
            user.password = passwordEncoder.encode(changePasswordRequest.newPassword)
            repository.save(user)
        } catch (ex: BadCredentialsException) {
            throw ResponseStatusException(
                HttpStatus.I_AM_A_TEAPOT,
                "Chybně zadané heslo"
            )
        }
    }

    fun updateProfile(updateProfileRequest: UpdateProfileRequest): TokenResponse {

        val user = findById(updateProfileRequest.id)
        user.firstName = updateProfileRequest.firstName
        user.lastName = updateProfileRequest.lastName

        repository.save(user)
        return TokenResponse(jwtTokenUtil.generateAccessToken(user))
    }

    fun delete(id: Long) = repository.delete(findById(id))

    override fun loadUserByUsername(userName: String): UserDetails? = repository.findUserByUserName(userName)

    fun areCredentialsRight(registerRequest: RegisterRequest): Boolean {
        return !(registerRequest.userName.isBlank() || registerRequest.firstName.isBlank() || registerRequest.lastName.isBlank() || registerRequest.password.isBlank())
    }

}



