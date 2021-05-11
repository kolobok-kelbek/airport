package com.kelbek.airport.security

import com.kelbek.airport.repository.UserRepository
import com.kelbek.airport.security.filter.JwtAuthenticationFilter
import com.kelbek.airport.security.filter.JwtAuthorizationFilter
import com.kelbek.airport.web.AuthCookieService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
@ComponentScan("com.kelbek.airport")
class SecurityConfig(
    private val userRepository: UserRepository,
    private val authCookieService: AuthCookieService,
    private val passwordEncoder: PasswordEncoder,
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/auth/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .addFilter(JwtAuthenticationFilter(userRepository, authCookieService, passwordEncoder))
            .addFilter(JwtAuthorizationFilter(authenticationManager(), authCookieService))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues())
        return source
    }
}