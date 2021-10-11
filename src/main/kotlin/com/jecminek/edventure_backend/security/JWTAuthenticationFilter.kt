package com.jecminek.edventure_backend.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.jecminek.edventure_backend.constant.SecurityConstants.EXPIRATION_TIME
import com.jecminek.edventure_backend.constant.SecurityConstants.SECRET
import com.jecminek.edventure_backend.domain.user.UserRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthenticationFilter(authenticationManager: AuthenticationManager) :
    UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?
    ): Authentication {
        return try {
            val credentials: UserRequest = ObjectMapper().readValue(request!!.inputStream, UserRequest::class.java)
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    credentials.email,
                    credentials.password,
                    ArrayList()
                )
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val token = JWT.create()
            .withSubject((authResult?.principal as UserRequest).email + (authResult.principal as UserRequest).password)
            .withExpiresAt(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(SECRET.toByteArray()))

        val body = "${(authResult.principal as UserRequest).email} $token"

        response?.writer?.write(body)
        response?.writer?.flush()
    }


}