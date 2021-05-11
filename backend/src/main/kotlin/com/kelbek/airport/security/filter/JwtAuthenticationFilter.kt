package com.kelbek.airport.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.kelbek.airport.model.query.SignIn
import com.kelbek.airport.repository.UserRepository
import com.kelbek.airport.web.AuthCookieService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.server.ResponseStatusException
import java.net.URLEncoder
import java.util.*
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val userRepository: UserRepository,
    private val authCookieService: AuthCookieService,
    private val passwordEncoder: PasswordEncoder,
) : UsernamePasswordAuthenticationFilter() {
    init {
        setFilterProcessesUrl("/auth/signin")
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication? {
        val mapper = ObjectMapper().registerModule(KotlinModule())
        val signInCommand: SignIn = mapper.readValue(request.inputStream, SignIn::class.java)

        val user: com.kelbek.airport.entity.User? = userRepository
            .findByUsername(signInCommand.username)

        if (null != user && passwordEncoder.matches(signInCommand.password, user.password)) {
            return UsernamePasswordAuthenticationToken(signInCommand.username, signInCommand.password, ArrayList())
        }

        authCookieService.clear(response!!, authCookieService.TOKEN_COOKIE)

        throw ResponseStatusException(HttpStatus.FORBIDDEN, "invalid data for authentication")
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        filterChain: FilterChain?,
        authentication: Authentication,
    ) {
        if (response == null) {
            return
        }

        val roles = authentication.authorities
            .stream()
            .map { obj: GrantedAuthority -> obj.authority }
            .collect(Collectors.toList())

        val jwt: String? = Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(authCookieService.JWT_SECRET.toByteArray()), SignatureAlgorithm.HS512)
            .setHeaderParam(authCookieService.TOKEN_PARAM_KEY_TYPE, authCookieService.TOKEN_TYPE)
            .setIssuer(authCookieService.TOKEN_ISSUER)
            .setAudience(authCookieService.TOKEN_AUDIENCE)
            .setSubject(authentication.principal.toString())
            .setExpiration(
                Date(System.currentTimeMillis() + authCookieService.TOKEN_LIFETIME_MILLISECOND)
            )
            .claim(authCookieService.TOKEN_PARAM_KEY_ROLES, roles)
            .compact()

        val token = URLEncoder.encode(authCookieService.TOKEN_PREFIX + jwt, Charsets.UTF_8)

        authCookieService.create(response, authCookieService.TOKEN_COOKIE, token)
    }
}