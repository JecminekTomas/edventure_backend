package com.jecminek.edventure_backend.security

import com.jecminek.edventure_backend.domain.user.User
import io.jsonwebtoken.*
import org.slf4j.Logger
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenUtil {
    private val jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP"
    private val logger: Logger? = null
    fun generateAccessToken(user: User): String {
        return Jwts.builder()
            .setSubject("${user.id}, ${user.userName}")
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun getUserId(token: String?): String {
        val claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
        return claims.subject.split(",").toTypedArray()[0]
    }

    fun getUsername(token: String?): String {
        val claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
        return claims.subject.split(",").toTypedArray()[1]
    }

    fun getExpirationDate(token: String?): Date {
        val claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
        return claims.expiration
    }

    fun validate(token: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            return true
        } catch (ex: SignatureException) {
            logger!!.error("Invalid JWT signature - {}", ex.message)
        } catch (ex: MalformedJwtException) {
            logger!!.error("Invalid JWT token - {}", ex.message)
        } catch (ex: ExpiredJwtException) {
            logger!!.error("Expired JWT token - {}", ex.message)
        } catch (ex: UnsupportedJwtException) {
            logger!!.error("Unsupported JWT token - {}", ex.message)
        } catch (ex: IllegalArgumentException) {
            logger!!.error("JWT claims string is empty - {}", ex.message)
        }
        return false
    }
}