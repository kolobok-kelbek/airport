package com.kelbek.airport.security

import com.kelbek.airport.repository.UserRepository
import com.kelbek.airport.security.filter.JwtAuthenticationFilter
import com.kelbek.airport.security.filter.JwtAuthorizationFilter
import com.kelbek.airport.web.AuthCookieService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*


@Configuration
@EnableWebSecurity
@ComponentScan("com.kelbek.airport")
class SecurityConfig(
        private val userRepository: UserRepository,
        private val authCookieService: AuthCookieService,
        private val passwordEncoder: PasswordEncoder,
) : WebSecurityConfigurerAdapter() {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun configure(http: HttpSecurity) {
        http
                .cors()
                .and()
                .logout()
                .logoutUrl("/auth/signout")
                .logoutSuccessHandler { request, response, authentication ->
                    run {
                        response.status = HttpStatus.NO_CONTENT.value()
                        authCookieService.clear(response, authCookieService.TOKEN_COOKIE)
                    }
                }
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/**")
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
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://airport.local")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}