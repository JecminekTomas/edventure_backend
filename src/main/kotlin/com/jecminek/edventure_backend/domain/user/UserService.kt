package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.domain.user.request.LoginRequest
import com.jecminek.edventure_backend.domain.user.request.RegisterRequest
import com.jecminek.edventure_backend.domain.user.request.UpdateRequest
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
        else {
            val user = User()
            user.userName = registerRequest.userName
            user.firstName = registerRequest.firstName
            user.lastName = registerRequest.lastName
            user.password = passwordEncoder.encode(registerRequest.password)
            user.setAuthority(AuthorityType.USER)
            return repository.save(user).convertEntityToResponse()
        }
    }

    fun login(@RequestBody request: @Valid LoginRequest): ResponseEntity<LoginResponse> {
        return try {
            val authenticate: Authentication = authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken(request.userName, request.password))
            val user = authenticate.principal as User

            ResponseEntity.ok()
                .body(LoginResponse(jwtTokenUtil.generateAccessToken(user)))

        } catch (ex: BadCredentialsException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

    fun update(updateRequest: UpdateRequest): UserResponse {
        if (repository.findUserByUserName(updateRequest.userName) != null)
            throw ResponseStatusException(
                HttpStatus.CONFLICT,
                "Uživatelské jméno ${updateRequest.userName} je již obsazené."
            )

        val user = findById(updateRequest.id)
        user.firstName = updateRequest.firstName
        user.lastName = updateRequest.lastName
        user.userName = updateRequest.userName

        return repository.save(user).convertEntityToResponse()
    }

    fun delete(id: Long) = repository.delete(findById(id))

    override fun loadUserByUsername(userName: String): UserDetails? = repository.findUserByUserName(userName)


}



