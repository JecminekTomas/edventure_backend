package com.jecminek.edventure_backend.config

import com.jecminek.edventure_backend.constant.SecurityConstants.SIGN_UP_URL
import com.jecminek.edventure_backend.security.JWTAuthenticationFilter
import com.jecminek.edventure_backend.security.JWTAuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@EnableWebSecurity(debug = true)
@Configuration
class WebSecurityConfig(
    private var userDetailsService: UserDetailsService,
    private var passwordEncoder: PasswordEncoder
) :
    WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
            .antMatchers(
                "/api-docs",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger-resources/**",
                "/configuration/security",
                "/configuration/ui"
            ).permitAll() // swagger api docs
            .anyRequest().authenticated()

            .and()

            .addFilter(JWTAuthenticationFilter(authenticationManager()))
            .addFilter(JWTAuthorizationFilter(authenticationManager())) // this disables session creation on Spring Security
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Throws(Exception::class)
    public override fun configure(auth: AuthenticationManagerBuilder) {
        // FIXME: userRepo.findByMail(mail)
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val corsConfiguration = CorsConfiguration().applyPermitDefaultValues()
        source.registerCorsConfiguration("/**", corsConfiguration)
        return source
    }
}