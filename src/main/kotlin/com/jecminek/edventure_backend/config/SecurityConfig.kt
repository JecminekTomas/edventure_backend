package com.jecminek.edventure_backend.config

import com.jecminek.edventure_backend.domain.user.UserRepository
import com.jecminek.edventure_backend.security.JwtTokenFilter
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
class SecurityConfig(
    userRepository: UserRepository,
    jwtTokenFilter: JwtTokenFilter
) : WebSecurityConfigurerAdapter() {
    private val userRepository: UserRepository
    private val jwtTokenFilter: JwtTokenFilter


    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(UserDetailsService { username: String ->
            userRepository.findUserByUserName(username)
        })
    }

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        // Enable CORS and disable CSRF
        var http: HttpSecurity = httpSecurity
        http = http.cors().and().csrf().disable()

        // Set session management to stateless
        http = http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

        // Set unauthorized requests exception handler
        http = http
            .exceptionHandling()
            .authenticationEntryPoint { _: HttpServletRequest?, response: HttpServletResponse, ex: AuthenticationException ->
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.message)
            }
            .and()

        // TODO: 12.10.2021 MORE MATCHERS, I.E. FACULTY, UNIVERSITY - FOR ADMIN ONLY
        // Set permissions on endpoints
        http.authorizeRequests() // Swagger endpoints must be publicly accessible
            .antMatchers("/register/**").permitAll()
            .antMatchers("/login/**").permitAll()
            .antMatchers(
                "/api-docs",
                "/api-docs/swagger-config",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger-resources/**",
                "/configuration/security",
                "/configuration/ui"
            ).permitAll() // swagger api docs
            //FIXME
            //.antMatchers("/universities").hasAuthority("USER")
            .anyRequest().authenticated()

        // Add JWT token filter
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    // Used by spring security if CORS is enabled.
    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOriginPattern("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }

    // Expose authentication manager bean
    @Bean
    override fun authenticationManagerBean(): AuthenticationManager = super.authenticationManagerBean()

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    init {
        this.userRepository = userRepository
        this.jwtTokenFilter = jwtTokenFilter

        // Inherit security context in async function calls
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL)
    }
}